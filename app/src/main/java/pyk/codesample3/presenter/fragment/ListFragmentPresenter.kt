package pyk.codesample3.presenter.fragment

import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList

class ListFragmentPresenter(val contractView: ListFragmentContract.ListFragmentView):
        ListFragmentContract.ListFragmentPresenter {
    
    private var pageNumber = 1
    override suspend fun pullNextPage(): MutableList<Movie> {
        return if(pageNumber < 10) {
            // TODO: when implementing network calls i'll likely have to only increment on success
            // increment page number with each request
            MovieList().pullPage(pageNumber++)
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