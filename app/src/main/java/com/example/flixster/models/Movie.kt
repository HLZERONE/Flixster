package com.example.flixster.models

import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class Movie(jsonObject: JSONObject) {
    var posterPath: String
    var title: String
    var overview: String
    var backdropPath: String

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
                movies.add(Movie(movieJsonArray.getJSONObject(i)))
            }
            return movies
        }
    }

    init {
        posterPath = jsonObject.getString("poster_path")
        title = jsonObject.getString("title")
        overview = jsonObject.getString("overview")
        backdropPath = jsonObject.getString("backdrop_path")
    }
}
