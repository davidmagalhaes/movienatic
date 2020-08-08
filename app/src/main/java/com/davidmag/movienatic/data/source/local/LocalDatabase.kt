package com.davidmag.movienatic.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.davidmag.movienatic.data.source.local.dao.GenreDao
import com.davidmag.movienatic.data.source.local.dao.ImageConfigsDao
import com.davidmag.movienatic.data.source.local.dao.MovieDao
import com.davidmag.movienatic.data.source.local.dto.GenreDb
import com.davidmag.movienatic.data.source.local.dto.ImageConfigsDb
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.data.source.local.dto.MovieGenreDb
import com.davidmag.movienatic.data.source.local.util.RoomConverters

@Database(
    entities = [
        GenreDb::class,
        MovieGenreDb::class,
        MovieDb::class,
        ImageConfigsDb::class
    ],
    version = 2,
    exportSchema = true
)
@TypeConverters(RoomConverters::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun getGenreDao(): GenreDao
    abstract fun getMovieDao(): MovieDao
    abstract fun getImageConfigsDao(): ImageConfigsDao
}