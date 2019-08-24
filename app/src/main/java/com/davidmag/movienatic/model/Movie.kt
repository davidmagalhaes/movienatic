package com.davidmag.movienatic.model

import com.google.gson.annotations.SerializedName
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
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
    @SerializedName("genres")
    var genres : RealmList<Genre>? = null
    @SerializedName("backdrop_path")
    var backdropPath : String? = null
    @SerializedName("title")
    var title: String? = null
    @SerializedName("overview")
    var overview: String? = null

    var lastUpdate : Date? = null

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

