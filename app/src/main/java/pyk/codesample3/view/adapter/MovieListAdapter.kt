package pyk.codesample3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pyk.codesample3.databinding.ItemMovielistBinding
import pyk.codesample3.model.item.Movie

class MovieListAdapter(private val clickListener: MovieListener,
                       private val checkedListener: CheckedListener): ListAdapter<Movie, MovieListAdapter.ViewHolder>(
        DiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(position, item, clickListener, checkedListener)
    }
    
    class ViewHolder private constructor(val b: ItemMovielistBinding): RecyclerView.ViewHolder(
            b.root) {
        
        fun bind(index: Int, item: Movie, clickListener: MovieListener,
                 checkedListener: CheckedListener) {
            b.movie = item
            b.index = index
            b.clickListener = clickListener
            b.checkedListener = checkedListener
            
            b.cbSelected.isChecked = item.isChecked
            b.executePendingBindings()
        }
        
        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val b = ItemMovielistBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(b)
            }
        }
    }
}

class DiffCallback: DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        // because i don't have ID's i compare title and release for uniqueness
        return ((oldItem.title == newItem.title) && (oldItem.release == newItem.release))
    }
    
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
    
}

class MovieListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}

class CheckedListener(val checkedListener: (index: Int) -> Unit) {
    fun onChecked(index: Int) = checkedListener(index)
}