package com.januar.submission2jetpack.ui.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.januar.submission2jetpack.BuildConfig
import com.januar.submission2jetpack.R
import com.januar.submission2jetpack.data.remote.response.movie.MovieResult
import com.januar.submission2jetpack.ui.details.DetailsActivity
import com.januar.submission2jetpack.ui.details.DetailsActivity.Companion.CLICK_ID
import com.januar.submission2jetpack.ui.details.DetailsActivity.Companion.EXTRA_ID
import kotlinx.android.synthetic.main.grid_movies.view.*

class MovieAdapter :RecyclerView.Adapter<MovieAdapter.MovieViewHolder>(){

    private var arrayList = ArrayList<MovieResult>()

    companion object{
        private const val IMAGE_URL = BuildConfig.IMAGE_URL
    }

    fun setMovie(list:List<MovieResult>){
        arrayList.clear()
        arrayList.addAll(list)
    }

    inner class MovieViewHolder(view: View): RecyclerView.ViewHolder(view){
        fun bind(movie: MovieResult){
            with(itemView){
                Glide.with(context)
                    .load(IMAGE_URL+movie.poster_path)
                    .error(R.drawable.baseline_broken_image_white_18dp)
                    .into(movie_poster)

                itemView.setOnClickListener {
                    Intent(context, DetailsActivity::class.java).also {
                        it.putExtra(CLICK_ID, 1)
                        it.putExtra(EXTRA_ID, movie.id)
                        itemView.context.startActivity(it)
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.grid_movies, parent, false)
        return MovieViewHolder(view)
    }

    override fun getItemCount(): Int = arrayList.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = arrayList[position]
        holder.bind(movie)
    }
}