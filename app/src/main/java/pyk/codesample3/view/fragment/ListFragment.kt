package pyk.codesample3.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import pyk.codesample3.R
import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.databinding.FragmentListBinding
import pyk.codesample3.model.SourceBridge
import pyk.codesample3.presenter.fragment.ListFragmentPresenter
import pyk.codesample3.view.adapter.MovieListAdapter
import pyk.codesample3.view.adapter.MovieListener

class ListFragment: Fragment(), ListFragmentContract.ListFragmentView {
    val presenter = ListFragmentPresenter(this)
    private val uiScope = CoroutineScope(Dispatchers.Main)
    private var loadingPage = false
    
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list,
                                                             container, false)
        b.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_listFragment_to_spinFragment)
        }
        
        setHasOptionsMenu(true)
        
        val adapter = MovieListAdapter(MovieListener { movie ->
            this.findNavController().navigate(ListFragmentDirections.actionListFragmentToDetailsFragment(movie))
        })
        b.rvList.adapter = adapter
        
        b.rvList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_DRAGGING) {
                    if(!loadingPage) {
                        loadingPage = true
                        uiScope.launch {
                            // this is necessary for DiffUtil to work since if i was using
                            // live data then every time a change occurred it would be treated like
                            // a new list was made, so i must also provide a "new" list
                            adapter.submitList(presenter.pullNextPage().toMutableList())
                            loadingPage = false
                        }
                    }
                }
            }
        })
        
        uiScope.launch { adapter.submitList(presenter.pullNextPage()) }
        
        return b.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
                                                     view!!.findNavController()) || super.onOptionsItemSelected(
                item)
    }
    
    override fun requestNextPage() {
    
    }
    
    override fun notifyEndOfPages() {
        Toast.makeText(activity, "No More Movies!", Toast.LENGTH_SHORT).show()
    }
}