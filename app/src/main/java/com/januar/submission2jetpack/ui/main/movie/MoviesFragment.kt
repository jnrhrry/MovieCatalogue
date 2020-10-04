package com.januar.submission2jetpack.ui.main.movie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.data.remote.response.movie.MovieResult
import com.januar.submission2jetpack.ui.adapter.MovieAdapter
import com.januar.submission2jetpack.utils.Listeners
import com.januar.submission2jetpack.utils.gone
import com.januar.submission2jetpack.utils.show
import com.januar.submission2jetpack.utils.snackBar
import com.januar.submission2jetpack.viewmodelfactory.MovieViewModelFactory
import kotlinx.android.synthetic.main.fragment_movie.*
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import org.kodein.di.android.x.kodein

class MoviesFragment : Fragment(), KodeinAware, Listeners{

    private val movieFactory: MovieViewModelFactory by instance()
    private lateinit var movieVM: MoviesViewModel
    private val movieAdapter = MovieAdapter()

    override val kodein by kodein()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        movieVM = ViewModelProvider(this, movieFactory).get(MoviesViewModel::class.java)
        getMovies()
        swipeMovies.setOnRefreshListener { getMovies() }
    }

    private fun getMovies(){
        movieVM.listener = this

        with(movie_rv){
            adapter = movieAdapter
            layoutManager = GridLayoutManager(context, 3)
            setHasFixedSize(true)
        }

        movieVM.getAllMovies().observe(this, Observer {
            this.movieAdapter.setMovie(it)
            movieAdapter.notifyDataSetChanged()
        })
    }

    override fun onStarted() {
        movie_progressbar.show()
    }

    override fun onFinished() {
        movie_progressbar.gone()
        swipeMovies.isRefreshing = false
    }

    override fun onError(message: String) {
        movie_home.snackBar(message)
        movie_progressbar.gone()
        swipeMovies.isRefreshing = false
    }
}