package com.davidmag.movienatic.data.source.local.dto

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
open class GenreDb {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "_genre_id")
    var id : Long? = null
    var name : String? = null
}