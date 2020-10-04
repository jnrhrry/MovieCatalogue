package com.januar.submission2jetpack.data.remote.api

import com.januar.submission2jetpack.data.remote.response.movie.MovieDetailsResponse
import com.januar.submission2jetpack.data.remote.response.movie.MovieResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesDetailsResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIHelper{

    @GET("discover/movie")
    suspend fun getMovieAPI(@Query("api_key") apiKey: String?): Response<MovieResponse>

    @GET("discover/tv")
    suspend fun getSeriesAPI(@Query("api_key") apiKey: String?): Response<SeriesResponse>

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(@Path("movie_id") id: Int, @Query("api_key") key: String):
            Response<MovieDetailsResponse>

    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(@Path("tv_id") id: Int, @Query("api_key") key: String):
            Response<SeriesDetailsResponse>

    companion object{
        operator fun invoke(interceptor: NetworkInterceptor): APIHelper{
            val okHttpClient = OkHttpClient.Builder().addInterceptor(interceptor)
                .build()

            return Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl("https://api.themoviedb.org/3/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(APIHelper::class.java)
        }
    }
}