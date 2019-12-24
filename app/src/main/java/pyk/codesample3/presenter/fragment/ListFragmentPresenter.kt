package pyk.codesample3.presenter.fragment

import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList

class ListFragmentPresenter(val contractView: ListFragmentContract.ListFragmentView):
        ListFragmentContract.ListFragmentPresenter {
    
    private var pageNumber = 1
    override suspend fun pullNextPage(): MutableList<Movie> {
        val movieList = MovieList()
        if(pageNumber < 10) {
            // TODO: when implementing network calls i'll likely have to only increment on success
            // increment page number with each request
            return movieList.pullPage(pageNumber++)
        } else {
            return movieList.getMovies()
        }
        
    }
}