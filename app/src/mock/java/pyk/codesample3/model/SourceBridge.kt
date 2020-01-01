package pyk.codesample3.model

import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.support.StaticValues

class SourceBridge {
    fun pullPage(pageNumber: Int): MutableList<Movie> {
        return when (pageNumber) {
            1    -> StaticValues.movies1
            2    -> StaticValues.movies2
            else -> mutableListOf()
        }
    }
}