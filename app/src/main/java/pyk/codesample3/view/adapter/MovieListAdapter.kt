package pyk.codesample3.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pyk.codesample3.R
import pyk.codesample3.model.Movie

class MovieListAdapter: RecyclerView.Adapter<MovieListAdapter.ViewHolder>() {
    
    lateinit var list: ArrayList<Movie>
    
    init {
        for(i in 0..20) {
            list.add(Movie())
        }
        notifyDataSetChanged()
    }
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater
                .inflate(R.layout.item_movielist, parent, false)
        return ViewHolder(view)
    }
    
    override fun getItemCount() = list.size
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
    }
    
    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    
    }
}