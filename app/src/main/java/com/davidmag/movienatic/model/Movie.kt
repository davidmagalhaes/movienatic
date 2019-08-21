package com.davidmag.movienatic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import io.realm.annotations.RealmField
import java.io.Serializable
import java.util.*

open class Movie : RealmObject(), Serializable {
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

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Movie) return false

        if (id != other.id) return false

        return true
    }

    override fun hashCode(): Int {
        return id ?: 0
    }
}

