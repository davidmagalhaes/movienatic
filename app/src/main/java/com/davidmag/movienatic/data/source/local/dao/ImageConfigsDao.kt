package com.davidmag.movienatic.data.source.local.dao

import androidx.room.*
import com.davidmag.movienatic.data.source.local.dto.ImageConfigsDb
import io.reactivex.Flowable

@Dao
interface ImageConfigsDao : BaseDao<ImageConfigsDb> {
    @Query("SELECT * FROM ImageConfigsDb")
    fun get() : Flowable<List<ImageConfigsDb>>

    @Transaction
    fun cache(vararg item : ImageConfigsDb) {
        _deleteAll()
        _insert(*item)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun _insert(vararg data : ImageConfigsDb) : List<Long>

    @Query("DELETE FROM ImageConfigsDb")
    fun _deleteAll()
}