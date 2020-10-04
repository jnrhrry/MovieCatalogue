package com.januar.submission2jetpack.ui.details

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.core.view.ViewCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.januar.submission2jetpack.BuildConfig
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.data.remote.response.movie.MovieDetailsResponse
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesDetailsResponse
import com.januar.submission2jetpack.utils.Listeners
import com.januar.submission2jetpack.utils.gone
import com.januar.submission2jetpack.utils.show
import com.januar.submission2jetpack.utils.snackBar
import com.januar.submission2jetpack.viewmodelfactory.DetailsViewModelFactory
import kotlinx.android.synthetic.main.activity_details.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class DetailsActivity : AppCompatActivity(), KodeinAware, Listeners {

    private val factory: DetailsViewModelFactory by instance()
    private lateinit var appBar: AppBarLayout
    private lateinit var toolbar: Toolbar
    private lateinit var collapsingToolbarLayout: CollapsingToolbarLayout

    companion object{
        const val IMAGE_URL = BuildConfig.IMAGE_URL_LARGE
        const val CLICK_ID = "click"
        const val EXTRA_ID = "extra"
    }

    override val kodein by kodein()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val detailVM = ViewModelProvider(this, factory).get(DetailsViewModel::class.java)

        if (intent.getIntExtra(CLICK_ID,1)==1){
            detailVM.listener=this
            detailVM.getMovieDetails(intent.getIntExtra(EXTRA_ID,0)).observe(this, Observer {
                    movieDetails -> movieBind(movieDetails)
            })
        } else{
            detailVM.listener=this
            detailVM.getSeriesDetails(intent.getIntExtra(EXTRA_ID,0)).observe(this, Observer {
                    seriesDetails -> seriesBind(seriesDetails)
            })
        }
        toolBar()
    }

    override fun onStarted() {
        details_progressbar.show()
    }

    override fun onFinished() {
        details_progressbar.gone()
    }

    override fun onError(message: String) {
        details_container.snackBar(message)
        details_progressbar.gone()
    }

    private fun toolBar(){
        toolbar = findViewById(R.id.toolbar)
        appBar = findViewById(R.id.app_bar)
        collapsingToolbarLayout = findViewById(R.id.toolbar_layout)

        appBar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener{_, verticalOffset ->
            if (collapsingToolbarLayout.height + verticalOffset < 2 * ViewCompat.getMinimumHeight(collapsingToolbarLayout)){
                collapsingToolbarLayout.setCollapsedTitleTextColor(resources.getColor(R.color.colorTextTertiary))
                toolbar.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            } else {
                toolbar.setBackgroundColor(Color.TRANSPARENT)
                collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE)
            }
        })
        toolbar.setNavigationIcon(R.drawable.baseline_arrow_back_ios_white_18dp)
        toolbar.setNavigationOnClickListener{onBackPressed()}
    }

    private fun movieBind(movies: MovieDetailsResponse){
        collapsingToolbarLayout.title = movies.originalTitle
        release_date.text = movies.releaseDate
        movie_rating.text = movies.voteAverage.toString()
        movie_overview.text = movies.overview

        Glide.with(this)
            .load(IMAGE_URL + movies.posterPath)
            .error(R.drawable.loader)
            .into(movie_detail_poster)
    }

    private fun seriesBind(series: SeriesDetailsResponse){
        collapsingToolbarLayout.title = series.originalName
        release_date.text = series.firstAirDate
        movie_rating.text = series.voteAverage.toString()
        movie_overview.text = series.overview

        Glide.with(this)
            .load(IMAGE_URL + series.posterPath)
            .error(R.drawable.loader)
            .into(movie_detail_poster)
    }
}