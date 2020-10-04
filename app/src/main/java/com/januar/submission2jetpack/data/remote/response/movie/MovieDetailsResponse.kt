package com.januar.submission2jetpack.data.remote.response.movie

import com.google.gson.annotations.SerializedName

data class MovieDetailsResponse(
    @SerializedName("original_title")
    var originalTitle: String,
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("release_date")
    val releaseDate: String,
    val video: Boolean,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)