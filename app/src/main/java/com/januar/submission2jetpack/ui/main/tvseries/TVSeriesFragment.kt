package com.januar.submission2jetpack.ui.main.tvseries

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.ui.adapter.SeriesAdapter
import com.januar.submission2jetpack.utils.Listeners
import com.januar.submission2jetpack.utils.gone
import com.januar.submission2jetpack.utils.show
import com.januar.submission2jetpack.utils.snackBar
import com.januar.submission2jetpack.viewmodelfactory.TVSeriesViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import kotlinx.android.synthetic.main.fragment_tv_series.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

class TVSeriesFragment : Fragment(), KodeinAware, Listeners {

    private val seriesFactory: TVSeriesViewModelFactory by instance()
    override val kodein by kodein()
    private val  seriesAdapter = SeriesAdapter()
    private lateinit var seriesVM: TVSeriesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_series, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        seriesVM = ViewModelProvider(this, seriesFactory).get(TVSeriesViewModel::class.java)
        getSeries()
        swipeSeries.setOnRefreshListener { getSeries() }
    }

    private fun getSeries(){
        seriesVM.listener = this

        with(series_rv){
            adapter = seriesAdapter
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
        }
        seriesVM.getAllSeries().observe(this, Observer{
            seriesAdapter.setSeries(it)
            seriesAdapter.notifyDataSetChanged()
        })
    }


    override fun onStarted() {
        series_progressbar.show()
    }

    override fun onFinished() {
        series_progressbar.gone()
        swipeSeries.isRefreshing = false
    }

    override fun onError(message: String) {
        series_home.snackBar(message)
        series_progressbar.gone()
        swipeSeries.isRefreshing = false
    }
}