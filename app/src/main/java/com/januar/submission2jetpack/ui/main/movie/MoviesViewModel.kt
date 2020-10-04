package com.januar.submission2jetpack.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.januar.submission2jetpack.data.remote.api.APIExceptions
import com.januar.submission2jetpack.data.remote.api.NoInternetExceptions
import com.januar.submission2jetpack.data.remote.response.movie.MovieResult
import com.januar.submission2jetpack.repository.Repository
import com.januar.submission2jetpack.utils.Coroutines
import com.januar.submission2jetpack.utils.Listeners

class MoviesViewModel(private val repo: Repository): ViewModel(){

    var listener: Listeners ?= null
    private val moviesLive = MutableLiveData<List<MovieResult>>()

    fun getAllMovies(): LiveData<List<MovieResult>>{
        Coroutines.main {
            try {
                moviesLive.postValue(repo.getMovies()?.results).let {
                    listener?.onFinished()
                }
            } catch(e:APIExceptions){
                listener?.onError(e.message!!)
            } catch (e:NoInternetExceptions){
                listener?.onError(e.message!!)
            }
        }
        return moviesLive
    }
}