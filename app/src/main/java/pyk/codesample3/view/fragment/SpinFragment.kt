package pyk.codesample3.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import pyk.codesample3.R
import pyk.codesample3.databinding.FragmentSpinBinding

class SpinFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentSpinBinding>(inflater, R.layout.fragment_spin, container,
                                                             false)
        
        return b.root
    }
}