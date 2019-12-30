package pyk.codesample3.util

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import pyk.codesample3.model.item.Movie

private const val imageUrl = "https://image.tmdb.org/t/p/w780"

@BindingAdapter("title") fun TextView.setTitle(item: Movie) {
    text = item.title
}

@BindingAdapter("release") fun TextView.setRelease(item: Movie) {
    text = item.release
}

@BindingAdapter("rating") fun TextView.setRating(item: Movie) {
    val t = item.rating + " / 10"
    text = t
}

@BindingAdapter("overview") fun TextView.setOverview(item: Movie) {
    text = item.overview
}

@BindingAdapter("poster") fun ImageView.setPoster(item: Movie) {
    Glide.with(this.context).load(imageUrl + item.posterPath).into(this)
}

@BindingAdapter("backdrop") fun ImageView.setBackdrop(item: Movie) {
    Glide.with(this.context).load(imageUrl + item.backdropPath).into(this)
}