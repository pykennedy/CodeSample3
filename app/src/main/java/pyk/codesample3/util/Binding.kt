package pyk.codesample3.util

import android.widget.TextView
import androidx.databinding.BindingAdapter
import pyk.codesample3.model.Movie

@BindingAdapter("title")
fun TextView.setTitle(item: Movie) {
    text = item.title
}

@BindingAdapter("release")
fun TextView.setRelease(item: Movie) {
    text = item.release
}

@BindingAdapter("rating")
fun TextView.setRating(item: Movie) {
    text = item.rating
}