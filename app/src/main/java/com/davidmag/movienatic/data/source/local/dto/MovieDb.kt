package com.davidmag.movienatic.data.source.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity
data class MovieDb  (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_movie_id")
    var id : Long? = null,

    var posterPath : String? = null,
    var releaseDate : LocalDate? = null,

    var backdropPath : String? = null,
    var title: String? = null,
    var overview: String? = null
)


