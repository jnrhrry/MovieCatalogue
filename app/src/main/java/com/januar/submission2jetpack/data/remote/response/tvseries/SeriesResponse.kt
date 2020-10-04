package com.januar.submission2jetpack.data.remote.response.tvseries

import com.google.gson.annotations.SerializedName

data class SeriesResponse(
    @SerializedName("results")
    val results: List<SeriesResult>
)