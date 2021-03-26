package com.appstuddio.cleanmvvm.data.remote

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface MoviesApi {
    companion object {
        private const val PARAM_MOVIE_ID = "movieId"
        private const val MOVIES = "upcoming"
        private const val KEY = "f46b58478f489737ad5a4651a4b25079"
        private const val MOVIE_DETAILS = "movie_0{$PARAM_MOVIE_ID}.json"
    }

    @GET(MOVIES)
    fun getMovies(
        @Query("api_key") key: String,
        @Query("page") page: String,
    ): Call<MovieEntity.Response>

    @GET(MOVIE_DETAILS) fun getMovieDetail(@Path(PARAM_MOVIE_ID) movieId: Int): Call<MovieDetailEntity.Response>
}