package pyk.codesample3.model.network

import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import pyk.codesample3.App
import pyk.codesample3.constants.Constants
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.item.TMDBJson
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class TMDBHelper {
    
    suspend fun getMovies(pageNumber: Int): MutableList<Movie> {
        var rawJson = ""
        val movies = mutableListOf<Movie>()
        rawJson = getRawJson(Constants.TMDB_URL + pageNumber.toString())
        
        return if (rawJson != "Error") {
            movieListFromJson(rawJson)
        } else {
            mutableListOf()
        }
    }
    
    private suspend fun getRawJson(url: String) = suspendCoroutine<String> { cont ->
        val queue = Volley.newRequestQueue(App.getAppContext())
        val request = StringRequest(Request.Method.GET, url,
                                    Response.Listener<String> { response -> cont.resume(response) },
                                    Response.ErrorListener { cont.resume("Error") })
        queue.add(request)
    }
    
    private fun movieListFromJson(rawJson: String): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        val gson = Gson()
        
        val tmdbJson = gson.fromJson(rawJson, TMDBJson::class.java)
        
        for (movie: Movie in tmdbJson.results) {
            movies.add(movie)
        }
        
        return movies
    }
}