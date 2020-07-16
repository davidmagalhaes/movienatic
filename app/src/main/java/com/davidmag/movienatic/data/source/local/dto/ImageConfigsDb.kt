package com.davidmag.movienatic.data.source.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class ImageConfigsDb {
    @PrimaryKey(autoGenerate = true)
    var id : Long = 1

    var lastUpdate : String? = null

    //@SerializedName("base_url")
    var baseUrl : String? = null
    //@SerializedName("secure_base_url")
    var secureBaseUrl : String? = null
    //@SerializedName("backdrop_sizes")
    var backdropSizes : List<String>? = null
    //@SerializedName("logo_sizes")
    var logoSizes : List<String>? = null
    //@SerializedName("poster_sizes")
    var posterSizes : List<String>? = null
}