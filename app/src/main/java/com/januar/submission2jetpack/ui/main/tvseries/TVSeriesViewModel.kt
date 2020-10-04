package com.januar.submission2jetpack.ui.main.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.submission2jetpack.data.remote.api.APIExceptions
import com.januar.submission2jetpack.data.remote.api.NoInternetExceptions
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesResult
import com.januar.submission2jetpack.repository.Repository
import com.januar.submission2jetpack.utils.Coroutines
import com.januar.submission2jetpack.utils.Listeners

class TVSeriesViewModel(private val repo: Repository): ViewModel(){

    var listener: Listeners?=null
    val seriesLive = MutableLiveData<List<SeriesResult>>()

    fun getAllSeries(): LiveData<List<SeriesResult>>{
        Coroutines.main {
            try {
                seriesLive.postValue(repo.getTVSeries().results)
                listener?.onFinished()
            } catch (e:APIExceptions){
                listener?.onError(e.message!!)
            } catch (e:NoInternetExceptions){
                listener?.onError(e.message!!)
            }
        }
        return seriesLive
    }
}