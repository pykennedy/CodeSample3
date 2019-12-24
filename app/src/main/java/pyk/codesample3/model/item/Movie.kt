package pyk.codesample3.model.item

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(val title: String = "Default", val release: String = "1980 - 01 - 01",
                 val rating: String = "9.9 / 10", val description: String = "Lorem Ipsum and stuff"): Parcelable