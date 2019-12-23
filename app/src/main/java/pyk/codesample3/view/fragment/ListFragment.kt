package pyk.codesample3.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import pyk.codesample3.R
import pyk.codesample3.databinding.FragmentListBinding
import pyk.codesample3.model.Movie
import pyk.codesample3.view.adapter.MovieListAdapter
import pyk.codesample3.view.adapter.MovieListener

class ListFragment: Fragment() {
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
    
        val list = mutableListOf<Movie>()
        for (i in 0..20) {
            list.add(Movie())
        }
        adapter.submitList(list)
        
        return b.root
    }
    
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item,
                                                     view!!.findNavController()) || super.onOptionsItemSelected(
                item)
    }
}