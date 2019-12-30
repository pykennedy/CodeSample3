package pyk.codesample3.view.fragment

import android.os.Bundle
import android.text.util.Linkify
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import pyk.codesample3.R
import pyk.codesample3.databinding.FragmentAboutBinding

class AboutFragment: Fragment() {
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val b = DataBindingUtil.inflate<FragmentAboutBinding>(inflater, R.layout.fragment_about,
                                                              container, false)
        
        Linkify.addLinks(b.tvAbout, Linkify.WEB_URLS)
        
        return b.root
    }
}