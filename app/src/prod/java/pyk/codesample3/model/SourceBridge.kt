package pyk.codesample3.model

import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.network.TMDBHelper

class SourceBridge {
    suspend fun pullPage(pageNumber: Int): MutableList<Movie> {
        val tmdbHelper = TMDBHelper()
        return tmdbHelper.getMovies(pageNumber)
    }
}