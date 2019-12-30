package pyk.codesample3.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import github.hellocsl.cursorwheel.CursorWheelLayout
import pyk.codesample3.R
import pyk.codesample3.contract.fragment.SpinFragmentContract

private const val imageUrl = "https://image.tmdb.org/t/p/w780"

class MovieSpinnerAdapter(val contractView: SpinFragmentContract.SpinFragmentView, val context: Context): CursorWheelLayout.CycleWheelAdapter() {
    var inflater: LayoutInflater
    init {
        inflater = LayoutInflater.from(context)
    }
    
    override fun getView(parent: View?, position: Int): View {
        val root = inflater.inflate(R.layout.item_spin, null, false) as View
        val image = root.findViewById(R.id.iv_spinner) as ImageView
        Glide.with(this.context)
                .load(imageUrl + contractView.getMovie(position).posterPath)
                .into(image)
        return root
    }
    
    override fun getItem(position: Int): Any {
        return contractView.getMovie(position)
    }
    
    override fun getCount(): Int {
        return contractView.getSize()
    }
}