package com.example.flixster.adapter

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flixster.R
import com.example.flixster.models.Movie


class MovieAdapter(var context: Context, movies: List<Movie>) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {
    var movies: List<Movie>
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        Log.d("Movie Adapter", "onCreatViewHolder")
        val movieView: View =
            LayoutInflater.from(context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(movieView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Movie Adapter", "onBindViewHolder$position")
        //Get the movie at the passed in position
        val movie: Movie = movies[position]
        //Bind the movie data into the ViewHolder
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movies.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvTitle: TextView
        var tvOverview: TextView
        var ivPoster: ImageView
        fun bind(movie: Movie) {
            tvTitle.setText(movie.title)
            tvOverview.setText(movie.overview)
            val imageUrl: String
            //if phone is in landscape
            imageUrl =
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
                    //then imageUrl = backdrop image
                    movie.getBackdropPath()
                } else {
                    movie.getPosterPath()
                }
            Glide.with(context).load(imageUrl).into(ivPoster)
        }

        init {
            tvTitle = itemView.findViewById(R.id.tvTitle)
            tvOverview = itemView.findViewById(R.id.tvOverview)
            ivPoster = itemView.findViewById(R.id.ivPoster)
        }
    }

    init {
        this.movies = movies
    }
}
