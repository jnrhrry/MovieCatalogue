package com.januar.submission2jetpack.data.remote.response.tvseries

import com.google.gson.annotations.SerializedName

data class SeriesResult(
    @SerializedName("first_air_date")
    val firstAirDate: String,
    val id: Int,

    @SerializedName("original_name")
    val originalName: String,
    val overview: String,

    @SerializedName("poster_path")
    val posterPath: String,

    @SerializedName("vote_average")
    val voteAverage: Double,

    @SerializedName("vote_count")
    val voteCount: Int
)