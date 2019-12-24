package pyk.codesample3.contract.fragment

import pyk.codesample3.model.item.Movie

interface ListFragmentContract {
    interface ListFragmentView {
        fun requestNextPage()
        fun notifyEndOfPages()
    }
    
    interface ListFragmentPresenter {
        suspend fun pullNextPage(): MutableList<Movie>
    }
}