package com.example.flixster.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList

@Parcelize
class Movie(
    var movieId : Int,
    private var posterPath: String,
    var title: String,
    var overview: String,
    var voteAverage: Double,
    var backdropPath: String) :Parcelable {

    @JvmName("getPosterPath1")
    fun getPosterPath(): String {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath)
    }

    @JvmName("getBackdropPath1")
    fun getBackdropPath(): String {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath)
    }

    companion object {
        @Throws(JSONException::class)
        fun fromJsonArray(movieJsonArray: JSONArray): List<Movie> {
            val movies: MutableList<Movie> = ArrayList()
            for (i in 0 until movieJsonArray.length()) {
                val movieJson = movieJsonArray.getJSONObject(i)
                movies.add(
                    Movie(
                        movieJson.getInt("id"),
                        movieJson.getString("poster_path"),
                        movieJson.getString("title"),
                        movieJson.getString("overview"),
                        movieJson.getDouble("vote_average"),
                        movieJson.getString("backdrop_path")
                    )
                )
            }
            return movies
        }
    }
}
