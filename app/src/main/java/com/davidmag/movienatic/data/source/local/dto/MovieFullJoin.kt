package com.davidmag.movienatic.data.source.local.dto

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class MovieFullJoin {
    @Embedded
    var movie: MovieDb? = null

    @Relation(
        parentColumn = "_movie_id",
        entityColumn = "_genre_id",
        entity = GenreDb::class,
        associateBy = Junction(
            value = MovieGenreDb::class,
            parentColumn = "movieId",
            entityColumn = "genreId"
        )
    )
    var genres: List<GenreDb>? = null
}