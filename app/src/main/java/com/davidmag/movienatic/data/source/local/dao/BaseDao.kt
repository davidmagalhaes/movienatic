package com.davidmag.movienatic.data.source.local.dao

import androidx.room.*
import io.reactivex.Maybe

interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(vararg item : T) : Maybe<List<Long>>

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insert(data : List<T>) : Maybe<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(vararg item : T) : Maybe<List<Long>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSync(vararg data : T) : List<Long>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsert(data : List<T>) : Maybe<List<Long>>

    @Delete
    fun delete(vararg item : T) : Maybe<Int>

    @Delete
    fun deleteSync(vararg item : T): Int

    @Update
    fun update(vararg item : T) : Maybe<Int>
}