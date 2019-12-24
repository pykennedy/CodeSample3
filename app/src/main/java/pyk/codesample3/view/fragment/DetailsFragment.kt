package pyk.codesample3.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import pyk.codesample3.R
import pyk.codesample3.databinding.FragmentDetailsBinding

class DetailsFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentDetailsBinding>(inflater, R.layout.fragment_details, container,
                                                             false)
        val movie = DetailsFragmentArgs.fromBundle(arguments!!).movie
        
        b.tvTitle.text = movie.title
        
        return b.root
    }
}