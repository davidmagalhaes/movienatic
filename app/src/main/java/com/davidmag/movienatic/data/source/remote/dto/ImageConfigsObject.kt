package com.davidmag.movienatic.data.source.remote.dto

import com.google.gson.annotations.SerializedName
import org.threeten.bp.OffsetDateTime


class ImageConfigsObject {
    var id : Int = 1

    var lastUpdate : OffsetDateTime = OffsetDateTime.now()

    @SerializedName("base_url")
    var baseUrl : String? = null
    @SerializedName("secure_base_url")
    var secureBaseUrl : String? = null
    @SerializedName("backdrop_sizes")
    var backdropSizes : List<String>? = null
    @SerializedName("logo_sizes")
    var logoSizes : List<String>? = null
    @SerializedName("poster_sizes")
    var posterSizes : List<String>? = null
}