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
import pyk.codesample3.R
import pyk.codesample3.contract.fragment.ListFragmentContract
import pyk.codesample3.databinding.FragmentListBinding
import pyk.codesample3.model.SourceBridge
import pyk.codesample3.view.adapter.MovieListAdapter
import pyk.codesample3.view.adapter.MovieListener

class ListFragment: Fragment(), ListFragmentContract.ListFragmentView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentListBinding>(inflater, R.layout.fragment_list,
                                                             container, false)
        b.fab.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_listFragment_to_spinFragment)
        }
        
        setHasOptionsMenu(true)
        
        val adapter = MovieListAdapter(MovieListener { movie ->
            this.findNavController().navigate(R.id.action_listFragment_to_detailsFragment)
        })
        b.rvList.adapter = adapter
        val sb = SourceBridge()
        
        b.rvList.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(!recyclerView.canScrollVertically(1)) {
                    Toast.makeText(activity, "ayyy lmao", Toast.LENGTH_SHORT).show()
                    // TODO: set a boolean that i'm attempting to pull more pages so i dont spam requests
                }
            }
        })
        
        adapter.submitList(sb.getMovies())
        
        return b.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
                                                     view!!.findNavController()) || super.onOptionsItemSelected(
                item)
    }
    
    override fun requestNextPage() {
    
    }
    
    override fun updateUI() {
    
    }
}