package pyk.codesample3.model

import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.support.StaticValues

class SourceBridge {
    fun getMovies(): MutableList<Movie> {
        return StaticValues.movies
    }
}