package com.januar.submission2jetpack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.januar.submission2jetpack.BuildConfig
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.data.remote.response.tvseries.SeriesResult
import com.januar.submission2jetpack.ui.details.DetailsActivity
import com.januar.submission2jetpack.ui.details.DetailsActivity.Companion.CLICK_ID
import com.januar.submission2jetpack.ui.details.DetailsActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.grid_tv_series.view.*

class SeriesAdapter: RecyclerView.Adapter<SeriesAdapter.SeriesViewHolder>(){

    private var arraySeries = ArrayList<SeriesResult>()

    companion object{
        private const val IMAGE_URL = BuildConfig.IMAGE_URL
    }

    fun setSeries(list: List<SeriesResult>){
        this.arraySeries.clear()
        this.arraySeries.addAll(list)
    }

    inner class SeriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
       fun bind(series: SeriesResult){
           with(itemView){
               Glide.with(itemView)
                   .load(IMAGE_URL + series.posterPath)
                   .error(R.drawable.baseline_broken_image_white_18dp)
                   .into(series_poster)

               itemView.setOnClickListener {
                   Intent(context, DetailsActivity::class.java).also {
                       it.putExtra(CLICK_ID, 0)
                       it.putExtra(EXTRA_ID, series.id)
                       itemView.context.startActivity(it)
                   }
               }
           }
       }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_tv_series, parent, false)

        return SeriesViewHolder(view)
    }

    override fun onBindViewHolder(holder: SeriesViewHolder, position: Int) {
        val series = arraySeries[position]
        holder.bind(series)
    }

    override fun getItemCount(): Int = arraySeries.size
}