package com.appstuddio.cleanmvvm.features.movies

data class MovieView(
    val id: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: Double,
    val releaseDate: String,
    val overview: String,
    val originalLanguage: String,
    )