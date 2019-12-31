package pyk.codesample3.presenter.fragment

import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList

class ListFragmentPresenter(val contractView: ListFragmentContract.ListFragmentView):
        ListFragmentContract.ListFragmentPresenter {
    
    private var pageNumber = 1
    override suspend fun pullNextPage(): MutableList<Movie> {
        return if (pageNumber < 10) {
            val movies = MovieList().pullPage(pageNumber++)
            if(movies.size > 0) {
                movies
            } else {
                pageNumber-- // because error
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