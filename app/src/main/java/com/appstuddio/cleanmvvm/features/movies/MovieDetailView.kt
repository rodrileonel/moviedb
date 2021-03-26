package com.appstuddio.cleanmvvm.features.movies

data class MovieDetailView(val id: Int,
                           val title: String,
                           val poster: String,
                           val summary: String,
                           val cast: String,
                           val director: String,
                           val year: Int,
                           val trailer: String)