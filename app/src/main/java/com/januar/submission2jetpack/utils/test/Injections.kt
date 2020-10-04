package com.januar.submission2jetpack.utils.test

import android.app.Application
import com.januar.submission2jetpack.data.remote.api.APIHelper
import com.januar.submission2jetpack.data.remote.api.NetworkInterceptor
import com.januar.submission2jetpack.repository.Repository
import com.januar.submission2jetpack.ui.details.DetailsViewModel
import com.januar.submission2jetpack.ui.main.movie.MoviesViewModel
import com.januar.submission2jetpack.ui.main.tvseries.TVSeriesViewModel
import com.januar.submission2jetpack.viewmodelfactory.DetailsViewModelFactory
import com.januar.submission2jetpack.viewmodelfactory.MovieViewModelFactory
import com.januar.submission2jetpack.viewmodelfactory.TVSeriesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class Injection : Application(), KodeinAware {
    override val kodein: Kodein = Kodein.lazy {
        import(androidXModule(this@Injection))

        //Interceptor Injection
        bind() from singleton { NetworkInterceptor(instance())}

        //Repo Injection
        bind() from singleton { Repository(instance()) }

        //APIHelper Injection
        bind() from singleton { APIHelper(instance()) }

        //MovieVM Injection
        bind() from provider { MoviesViewModel(instance()) }
        bind() from provider { MovieViewModelFactory(instance()) }

        //SeriesVM Injection
        bind() from provider { TVSeriesViewModel(instance()) }
        bind() from provider { TVSeriesViewModelFactory(instance()) }

        //DetailsVM Injection
        bind() from provider { DetailsViewModel(instance()) }
        bind() from provider { DetailsViewModelFactory(instance()) }
    }
}