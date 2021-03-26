package com.appstuddio.cleanmvvm.features.movies.usecase

import com.appstuddio.cleanmvvm.core.interactor.UseCase
import com.appstuddio.cleanmvvm.data.repository.MoviesRepository
import com.appstuddio.cleanmvvm.features.movies.models.MovieDetail
import javax.inject.Inject

class GetMovieDetail
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<MovieDetail, GetMovieDetail.Params>() {

    override suspend fun run(params: Params) = moviesRepository.movieDetails(params.id)

    data class Params(val id: Int)
}
