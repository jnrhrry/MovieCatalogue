package com.januar.submission2jetpack.repository

import com.januar.submission2jetpack.BuildConfig
import com.januar.submission2jetpack.data.remote.api.APIHelper
import com.januar.submission2jetpack.data.remote.api.APIRequest
import com.januar.submission2jetpack.data.remote.response.movie.MovieDetailsResponse
import com.januar.submission2jetpack.data.remote.response.movie.MovieResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesDetailsResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesResponse

class Repository(private val api:APIHelper): APIRequest(){
    companion object{
        const val API_KEY = BuildConfig.API_KEY
    }

    suspend fun getMovies():MovieResponse?{
        return apiRequest { api.getMovieAPI(API_KEY) }
    }

    suspend fun getTVSeries(): SeriesResponse{
        return apiRequest { api.getSeriesAPI(API_KEY) }
    }

    suspend fun getMovieDetails(id:Int): MovieDetailsResponse{
        return apiRequest { api.getMovieDetails(id, API_KEY) }
    }

    suspend fun getSeriesDetails(id: Int): SeriesDetailsResponse{
        return apiRequest { api.getSeriesDetails(id, API_KEY) }
    }
}