package com.appstuddio.cleanmvvm.data.repository

import com.appstuddio.cleanmvvm.core.functional.Either
import com.appstuddio.cleanmvvm.core.functional.Failure
import com.appstuddio.cleanmvvm.core.functional.NetworkHandler
import com.appstuddio.cleanmvvm.core.functional.Prefs
import com.appstuddio.cleanmvvm.data.local.MovieDataSource
import com.appstuddio.cleanmvvm.data.remote.MovieDetailEntity
import com.appstuddio.cleanmvvm.data.remote.MoviesService
import com.appstuddio.cleanmvvm.features.movies.models.Movie
import com.appstuddio.cleanmvvm.features.movies.models.MovieDetail
import retrofit2.Call
import javax.inject.Inject

interface MoviesRepository {
    fun movies(): Either<Failure, List<Movie>>
    fun movieDetails(movieId: Int): Either<Failure, MovieDetail>
    fun setData(): Either<Failure, Boolean>

    class Network
    @Inject constructor(private val networkHandler: NetworkHandler,
                        private val prefs: Prefs,
                        private val service: MoviesService,
                        private val movieDataSource: MovieDataSource
    ) : MoviesRepository {

        override fun movies(): Either<Failure, List<Movie>> {
            return Either.Right(movieDataSource.getMovies())
        }

        override fun setData(): Either<Failure, Boolean> {
            return when (networkHandler.isConnected) {
                true ->{
                    try {
                        val response = service.getMovies("f46b58478f489737ad5a4651a4b25079","1").execute()
                        when (response.isSuccessful) {
                            true -> {
                                response.body()?.let {
                                    prefs.age = 30
                                    prefs.name = "Rodrigo"
                                    it.results.map { movieEntity ->
                                        movieDataSource.insertMovies(movieEntity.toMovie())
                                    }
                                    Either.Right(true)
                                }?: Either.Left(Failure.DefaultError(""))
                            }
                            false -> Either.Left(Failure.ServerError)
                        }
                    } catch (e: Exception) {
                        Either.Left(Failure.DefaultError(e.message!!))
                    }
                }
                false -> Either.Left(Failure.NetworkConnection)
            }
        }

        override fun movieDetails(movieId: Int): Either<Failure, MovieDetail> {
            return when (networkHandler.isConnected) {
                true -> request(service.getMovieDetail(movieId), {
                    it.toMovieDetail()
                }, MovieDetailEntity.Response())
                false -> Either.Left(Failure.NetworkConnection)
            }
        }

        private fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure, R> {
            return try {
                val response = call.execute()
                when (response.isSuccessful) {
                    true -> Either.Right(transform((response.body() ?: default)))
                    false -> Either.Left(Failure.ServerError)
                }
            } catch (exception: Throwable) {
                Either.Left(Failure.ServerError)
            }
        }
    }
}