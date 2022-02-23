package com.example.flixster

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler.JSON
import com.example.flixster.adapter.MovieAdapter
import com.example.flixster.models.Movie
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.ArrayList


class MainActivity : AppCompatActivity() {
    var movies: MutableList<Movie>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rvMovies = findViewById<RecyclerView>(R.id.rvMovies)
        movies = ArrayList<Movie>()

        //Create the adapter
        val movieAdapter = MovieAdapter(this, movies as ArrayList<Movie>)

        //Set the adapter on the Recycler view
        rvMovies.adapter = movieAdapter

        //Set a Layout Manager on the recycler view
        rvMovies.layoutManager = LinearLayoutManager(this)
        val client = AsyncHttpClient()
        client[NOW_PLAYING_URL, object : JsonHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Headers, json: JSON) {
                Log.d(TAG, "onSuccess")
                val jsonObject = json.jsonObject
                try {
                    val results = jsonObject.getJSONArray("results")
                    Log.i(TAG, "Results: $results")
                    movies!!.addAll(Movie.fromJsonArray(results))
                    movieAdapter.notifyDataSetChanged()
                    Log.i(TAG, "Movies: " + movies!!.size)
                } catch (e: JSONException) {
                    Log.e(TAG, "Hit json exception", e)
                    e.printStackTrace()
                }
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers,
                response: String,
                throwable: Throwable
            ) {
                Log.d(TAG, "onFailure")
            }
        }]
    }

    companion object {
        const val NOW_PLAYING_URL =
            "https://api.themoviedb.org/3/movie/now_playing?api_key=a07e22bc18f5cb106bfe4cc1f83ad8ed"
        const val TAG = "MainActivity"
    }
}