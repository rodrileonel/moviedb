package com.appstuddio.cleanmvvm.data.remote

import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MoviesService
@Inject constructor(retrofit: Retrofit) : MoviesApi {
    private val moviesApi by lazy { retrofit.create(MoviesApi::class.java) }

    override fun getMovies(key: String, page: String) = moviesApi.getMovies(key,page)
    override fun getMovieDetail(movieId: Int) = moviesApi.getMovieDetail(movieId)
}
