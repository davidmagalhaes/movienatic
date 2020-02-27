package com.davidmag.movienatic.domain.model

import org.threeten.bp.OffsetDateTime

data class ImageConfigs (
    var id : Int = 1,

    var lastUpdate : OffsetDateTime = OffsetDateTime.now(),

    var baseUrl : String? = null,
    var secureBaseUrl : String? = null,
    var backdropSizes : List<String>? = null,
    var logoSizes : List<String>? = null,
    var posterSizes : List<String>? = null
)