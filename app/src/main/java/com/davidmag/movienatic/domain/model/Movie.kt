package com.davidmag.movienatic.domain.model

import org.threeten.bp.LocalDate

data class Movie  (
    var id : Int? = null,
    var posterPath : String? = null,
    var releaseDate : LocalDate? = null,
    var genres : List<Genre>? = null,
    var backdropPath : String? = null,
    var title: String? = null,
    var overview: String? = null,
    var lastUpdate : LocalDate? = null
)

