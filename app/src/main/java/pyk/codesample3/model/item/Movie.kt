package pyk.codesample3.model.item

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize data class Movie(val title: String = "Default",
                            @SerializedName("release_date") val release: String = "1980 - 01 - 01",
                            @SerializedName("vote_average")
                            val rating: String = "9.9 / 10",
                            val overview: String = "Lorem Ipsum and stuff",
                            @SerializedName("poster_path") val posterPath: String = "",
                            @SerializedName("backdrop_path") val backdropPath: String = "",
                            var isChecked: Boolean): Parcelable