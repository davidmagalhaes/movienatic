package com.davidmag.movienatic.model

import android.os.Parcel
import android.os.Parcelable
import com.davidmag.movienatic.util.DateUtils
import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class Movie() : RealmObject(), Serializable, Parcelable {
    @PrimaryKey
    @SerializedName("id")
    var id : Int? = null
    @SerializedName("poster_path")
    var posterPath : String? = null
    @SerializedName("release_date")
    var releaseDate : Date? = null
    @SerializedName("genre_ids")
    var genres : RealmList<Int>? = null
    @SerializedName("original_title")
    var originalTitle : String? = null
    @SerializedName("original_language")
    var originalLanguage : String? = null
    @SerializedName("backdrop_path")
    var backdropPath : String? = null
    @SerializedName("popularity")
    var popularity : Double? = null
    @SerializedName("vote_count")
    var voteCount : Int? = null
    @SerializedName("video")
    var video : Boolean? = null
    @SerializedName("vote_average")
    var voteAverage: Double? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("overview")
    var overview: String? = null
    @SerializedName("adult")
    var adult: Boolean? = null

    constructor(parcel: Parcel) : this() {
        id = parcel.readValue(Int::class.java.classLoader) as? Int
        posterPath = parcel.readString()
        releaseDate = DateUtils.fromIsoString(parcel.readString())

        parcel.readArray(Movie::class.java.classLoader)?.let { array ->
            genres = RealmList()
            array.forEach { genres!!.add(it as Int) }
        }

        originalTitle = parcel.readString()
        originalLanguage = parcel.readString()
        backdropPath = parcel.readString()
        popularity = parcel.readValue(Double::class.java.classLoader) as? Double
        voteCount = parcel.readValue(Int::class.java.classLoader) as? Int
        video = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
        voteAverage = parcel.readValue(Double::class.java.classLoader) as? Double
        title = parcel.readString()
        overview = parcel.readString()
        adult = parcel.readValue(Boolean::class.java.classLoader) as? Boolean
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeValue(id)
        parcel.writeString(posterPath)
        parcel.writeString(DateUtils.toIsoString(releaseDate))
        parcel.writeArray(genres?.toArray())
        parcel.writeString(originalTitle)
        parcel.writeString(originalLanguage)
        parcel.writeString(backdropPath)
        parcel.writeValue(popularity)
        parcel.writeValue(voteCount)
        parcel.writeValue(video)
        parcel.writeValue(voteAverage)
        parcel.writeString(title)
        parcel.writeString(overview)
        parcel.writeValue(adult)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}

