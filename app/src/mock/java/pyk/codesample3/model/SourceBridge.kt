package pyk.codesample3.model

import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.support.StaticValues

class SourceBridge {
    suspend fun pullPage(pageNumber: Int): MutableList<Movie> {
        if(pageNumber == 1) {
            return StaticValues.movies
        } else {
            return StaticValues.movies2
        }
        
    }
}