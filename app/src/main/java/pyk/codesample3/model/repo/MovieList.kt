package pyk.codesample3.model.repo

import pyk.codesample3.model.SourceBridge
import pyk.codesample3.model.item.Movie

class MovieList {
    
    // private singleton so that bob from down the street can't mess with the movie list
    // but anyone with the keys (instantiating a MovieList) can access the list
    private object Movies {
        
        val list = mutableListOf<Movie>()
    }
    
    fun getMovies(): MutableList<Movie> {
        return Movies.list
    }
    
    fun getCheckedMovies(): List<Movie> {
        return Movies.list.filter { it.isChecked }
    }
    
    fun getMovie(index: Int): Movie {
        return Movies.list[index]
    }
    
    fun clearChecks() {
        for (movie in Movies.list.filter { it.isChecked }) {
            movie.isChecked = false
        }
    }
    
    suspend fun pullPage(pageNumber: Int): MutableList<Movie> {
        // SourceBridge is a mocked class that allows me to easily switch between test/prod
        // configurations by intercepting the flow before we get to the true data source
        // This allows me to maintain 2 mocked files instead of n many
        val sb = SourceBridge()
        val temp = sb.pullPage(pageNumber)
        return if (temp.size > 0) {
            Movies.list.addAll(temp)
            Movies.list
        } else {
            temp
        }
    }
}