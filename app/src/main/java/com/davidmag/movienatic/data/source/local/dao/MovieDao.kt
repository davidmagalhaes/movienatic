package com.davidmag.movienatic.data.source.local.dao

import androidx.room.*
import com.davidmag.movienatic.data.source.local.dto.*
import io.reactivex.Flowable

@Dao
interface MovieDao : BaseDao<MovieDb> {
    @Query("SELECT * FROM MovieDb LEFT JOIN MovieGenreDb ON (MovieDb._movie_id = MovieGenreDb.movieId) LEFT JOIN GenreDb ON (MovieGenreDb.genreId = GenreDb._genre_id)")
    fun get(): Flowable<List<MovieFullJoin>>

    @Query("SELECT * FROM MovieDb LEFT JOIN MovieGenreDb ON (MovieDb._movie_id = MovieGenreDb.movieId) LEFT JOIN GenreDb ON (MovieGenreDb.genreId = GenreDb._genre_id) WHERE GenreDb._genre_id = :genreId")
    fun get(genreId: Long): Flowable<List<MovieFullJoin>>

    @Query("SELECT * FROM MovieDb LEFT JOIN MovieGenreDb ON (MovieDb._movie_id = MovieGenreDb.movieId) LEFT JOIN GenreDb ON (MovieGenreDb.genreId = GenreDb._genre_id) WHERE MovieDb._movie_id = :id")
    fun find(id : Long): Flowable<List<MovieFullJoin>>

    @Transaction
    fun cacheWithGenres(movies: List<MovieFullJoin>){
        _deleteAll()
        insertSync(*movies.map { it.movie!! }.toTypedArray())

        movies.forEach { each ->
            each.genres?.let { genres ->
                _insertGenres(genres.map {
                    MovieGenreDb(
                        movieId = each.movie?.id!!,
                        genreId = it.id!!
                    )
                })
            }
        }
    }

    @Transaction
    fun upsertWithGenres(movies: List<MovieFullJoin>){
        _deleteGenresFrom(movies.map { it.movie?.id!! })
        insertSync(*movies.map { it.movie!! }.toTypedArray())

        movies.forEach { each ->
            each.genres?.let { genres ->
                _insertGenres(genres.map {
                    MovieGenreDb(
                        movieId = each.movie?.id!!,
                        genreId = it.id!!
                    )
                })
            }
        }
    }

    @Query("DELETE FROM MovieDb")
    fun _deleteAll(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun _insertGenres(relations: List<MovieGenreDb>)

    @Query("DELETE FROM MovieGenreDb WHERE movieId in (:movieIds)")
    fun _deleteGenresFrom(movieIds: List<Long>): Int
}