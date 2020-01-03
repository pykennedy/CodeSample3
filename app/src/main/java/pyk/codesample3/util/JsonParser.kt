package pyk.codesample3.util

import com.google.gson.Gson
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.item.TMDBJson

class JsonParser {
    fun movieListFromJson(rawJson: String): MutableList<Movie> {
        val movies = mutableListOf<Movie>()
        val gson = Gson()
        
        val tmdbJson = gson.fromJson(rawJson, TMDBJson::class.java)
        
        for (movie: Movie in tmdbJson.results) {
            movies.add(movie)
        }
        
        return movies
    }
}