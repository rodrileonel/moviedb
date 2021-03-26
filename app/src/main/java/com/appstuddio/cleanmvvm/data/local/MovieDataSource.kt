package com.appstuddio.cleanmvvm.data.local

import com.appstuddio.cleanmvvm.data.local.dao.MovieDao
import com.appstuddio.cleanmvvm.features.movies.models.Movie
import javax.inject.Inject

class MovieDataSource @Inject constructor(private val movieDao: MovieDao){
    fun insertMovies(movie: Movie){
        movieDao.insertMovie(movie)
    }
    fun getMovies():List<Movie> {
        return movieDao.getMovies()
    }
}
