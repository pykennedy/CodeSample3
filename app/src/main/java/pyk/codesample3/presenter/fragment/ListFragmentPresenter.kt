package pyk.codesample3.presenter.fragment

import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList

class ListFragmentPresenter(private val contractView: ListFragmentContract.ListFragmentView,
                            private val maxPages: Int = 10): ListFragmentContract.ListFragmentPresenter {
    
    private var pageNumber = 1
    override suspend fun pullNextPage(): MutableList<Movie> {
        return if (pageNumber <= maxPages) {
            val movies = MovieList().pullPage(pageNumber)
            if (movies.size > 0) {
                pageNumber+=1
                movies
            } else {
                contractView.notifyBadPull()
                MovieList().getMovies()
            }
        } else {
            contractView.notifyEndOfPages()
            MovieList().getMovies()
        }
    }
    
    override fun setChecked(index: Int) {
        val movie = MovieList().getMovie(index)
        movie.isChecked = !movie.isChecked
    }
    
    override fun getMovies(): List<Movie> {
        return MovieList().getMovies()
    }
    
    override fun getCheckedMovies(): List<Movie> {
        return MovieList().getCheckedMovies()
    }
}