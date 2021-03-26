package com.appstuddio.cleanmvvm.data.remote

import com.appstuddio.cleanmvvm.features.movies.models.Movie
import com.google.gson.annotations.SerializedName

class MovieEntity {
    data class Response(
        @SerializedName("page") val page: Long,
        @SerializedName("results") val results: List<Result>,
        @SerializedName("total_pages") val totalPages: Long,
        @SerializedName("total_results") val totalResults: Long

    )

    data class Result (
        @SerializedName("id") val id: Int,
        @SerializedName("poster_path") val posterPath: String,
        @SerializedName("title") val title: String,
        @SerializedName("vote_average") val voteAverage: Double,
        @SerializedName("release_date") val releaseDate: String,
        @SerializedName("overview") val overview: String,
        @SerializedName("original_language") val originalLanguage: String,

        @SerializedName("adult")val adult: Boolean,
        @SerializedName("backdrop_path") val backdropPath: String,
        @SerializedName("genre_ids") val genreIDS: List<Long>,
        @SerializedName("original_title") val originalTitle: String,
        @SerializedName("popularity") val popularity: Double,
        @SerializedName("video") val video: Boolean,
        @SerializedName("vote_count") val voteCount: Long
    ){
        fun toMovie() = Movie(id,posterPath,title,voteAverage,releaseDate,overview,originalLanguage)
    }
}