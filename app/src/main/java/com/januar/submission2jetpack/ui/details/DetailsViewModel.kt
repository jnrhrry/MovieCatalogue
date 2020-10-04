package com.januar.submission2jetpack.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.submission2jetpack.data.remote.api.APIExceptions
import com.januar.submission2jetpack.data.remote.api.NoInternetExceptions
import com.januar.submission2jetpack.data.remote.response.movie.MovieDetailsResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesDetailsResponse
import com.januar.submission2jetpack.repository.Repository
import com.januar.submission2jetpack.utils.Coroutines
import com.januar.submission2jetpack.utils.Listeners

class DetailsViewModel (private val  repo: Repository): ViewModel(){

    var listener: Listeners ?= null
    private val movieDetailLive = MutableLiveData<MovieDetailsResponse>()
    private val seriesDetailLive = MutableLiveData<SeriesDetailsResponse>()

    fun getMovieDetails(id: Int): LiveData<MovieDetailsResponse>{
        Coroutines.main {
            try {
                movieDetailLive.postValue(repo.getMovieDetails(id))
                listener?.onFinished()
            } catch (e: APIExceptions) {
                listener?.onError(e.message!!)
            } catch (e:NoInternetExceptions){
                listener?.onError(e.message!!)
            }
        }
        return movieDetailLive
    }

    fun getSeriesDetails(id: Int): LiveData<SeriesDetailsResponse> {
        Coroutines.main {
            try {
                seriesDetailLive.postValue(repo.getSeriesDetails(id))
                listener?.onFinished()
            } catch (e: Exception) {
                listener?.onError(e.message!!)
            } catch (e: NoInternetExceptions) {
                listener?.onError(e.message!!)
            }
        }
    return seriesDetailLive
    }
}