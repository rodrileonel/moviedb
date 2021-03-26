package com.appstuddio.cleanmvvm.features.movies.usecase

import com.appstuddio.cleanmvvm.core.interactor.UseCase
import com.appstuddio.cleanmvvm.data.repository.MoviesRepository
import javax.inject.Inject

class SetData
@Inject constructor(private val moviesRepository: MoviesRepository) : UseCase<Boolean, UseCase.None>() {

    override suspend fun run(params: None) = moviesRepository.setData()

}