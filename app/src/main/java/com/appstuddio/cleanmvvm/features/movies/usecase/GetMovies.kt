package com.appstuddio.cleanmvvm.features.movies.usecase

import com.appstuddio.cleanmvvm.core.interactor.UseCase
import com.appstuddio.cleanmvvm.data.repository.MoviesRepository
import com.appstuddio.cleanmvvm.features.movies.models.Movie
import javax.inject.Inject

class GetMovies
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<List<Movie>, UseCase.None>() {

    override suspend fun run(params: None) = moviesRepository.movies()
}