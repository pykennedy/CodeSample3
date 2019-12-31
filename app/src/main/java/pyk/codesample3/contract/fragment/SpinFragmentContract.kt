package pyk.codesample3.contract.fragment

import pyk.codesample3.model.item.Movie

interface SpinFragmentContract {
    interface SpinFragmentView {
        fun getCheckedMovies(): List<Movie>
        fun getSize(): Int
        fun getMovie(index: Int): Movie
    }
}