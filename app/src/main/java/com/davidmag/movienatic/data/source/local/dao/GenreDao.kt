package com.davidmag.movienatic.data.source.local.dao

import androidx.room.*
import com.davidmag.movienatic.data.source.local.dto.GenreDb
import io.reactivex.Flowable

@Dao
interface GenreDao : BaseDao<GenreDb> {
    @Query("SELECT * FROM GenreDb")
    fun get() : Flowable<List<GenreDb>>

    @Transaction
    fun cache(vararg item : GenreDb) {
        _deleteAll()
        _insert(*item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun _insert(vararg data : GenreDb) : List<Long>

    @Query("DELETE FROM GenreDb")
    fun _deleteAll()
}