package com.davidmag.movienatic.data.source.local.dto

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.ForeignKey.CASCADE

@Entity(
    primaryKeys = [
        "movieId", "genreId"
    ],
    foreignKeys = [
        ForeignKey(
            parentColumns = ["_movie_id"],
            childColumns = ["movieId"],
            entity = MovieDb::class,
            onDelete = CASCADE
        ),
        ForeignKey(
            parentColumns = ["_genre_id"],
            childColumns = ["genreId"],
            entity = GenreDb::class,
            onDelete = CASCADE
        )
    ]
)
data class MovieGenreDb (
    val movieId : Long,
    val genreId : Long
)