package pyk.codesample3.contract.fragment

interface ListFragmentContract {
    interface ListFragmentView {
        fun requestNextPage()
        fun updateUI()
    }
    
    interface ListFragmentPresenter {
        fun pullNextPage()
    }
}