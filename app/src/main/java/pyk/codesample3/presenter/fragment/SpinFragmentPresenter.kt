package pyk.codesample3.presenter.fragment

import pyk.codesample3.contract.fragment.SpinFragmentContract
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList

class SpinFragmentPresenter: SpinFragmentContract.SpinFragmentPresenter {
    override fun getCheckedMovies(): List<Movie> {
        return MovieList().getCheckedMovies()
    }
    
    override fun getSize(): Int {
        return MovieList().getCheckedMovies().size
    }
    
    override fun getMovie(index: Int): Movie {
        return MovieList().getCheckedMovies()[index]
    }
    
}