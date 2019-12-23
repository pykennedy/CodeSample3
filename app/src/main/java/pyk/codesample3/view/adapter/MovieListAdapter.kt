package pyk.codesample3.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import pyk.codesample3.databinding.ItemMovielistBinding
import pyk.codesample3.model.Movie

class MovieListAdapter(val clickListener: MovieListener): ListAdapter<Movie, MovieListAdapter.ViewHolder>(DiffCallback()) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }
    
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, clickListener)
    }
    
    class ViewHolder private constructor(val b: ItemMovielistBinding): RecyclerView.ViewHolder(
            b.root) {
        
        fun bind(item: Movie, clickListener: MovieListener) {
            b.movie = item
            b.clickListener = clickListener
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
        // because i don't have ID's
        return ((oldItem.title == newItem.title) && (oldItem.release == newItem.release) && (oldItem.rating == newItem.rating))
    }
    
    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
    
}

class MovieListener(val clickListener: (movie: Movie) -> Unit) {
    fun onClick(movie: Movie) = clickListener(movie)
}