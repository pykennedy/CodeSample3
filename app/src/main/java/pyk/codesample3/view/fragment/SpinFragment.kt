package pyk.codesample3.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pyk.codesample3.R
import pyk.codesample3.contract.fragment.SpinFragmentContract
import pyk.codesample3.databinding.FragmentSpinBinding
import pyk.codesample3.model.item.Movie
import pyk.codesample3.model.repo.MovieList
import pyk.codesample3.view.adapter.MovieSpinnerAdapter

class SpinFragment: Fragment(), SpinFragmentContract.SpinFragmentView {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentSpinBinding>(inflater, R.layout.fragment_spin,
                                                             container, false)
        
        b.cwlMoviespinner.setAdapter(MovieSpinnerAdapter(this, requireContext()))
        // naming these "_" because android studio said it was a good idea so were rolling with it
        b.cwlMoviespinner.setOnMenuSelectedListener { _, _, pos ->
            if (b.movie != null) {
                b.movie = getMovie(pos)
            }
        }
        
        b.tvOverview.setOnClickListener {
            if (b.movie != null) {
                this.findNavController().navigate(
                        SpinFragmentDirections.actionSpinFragmentToDetailsFragment(
                                getMovie(b.cwlMoviespinner.selectedPosition)))
            }
        }
        
        // databinding bugs out if i don't initialize a movie before
        // setOnMenuSelectedListener is called (which occurs after onCreateView()'s return)
        b.movie = getMovie(0)
        
        return b.root
    }
    
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