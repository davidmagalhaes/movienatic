package com.davidmag.movienatic.data.source.local.impl

import com.davidmag.movienatic.data.source.boundary.local.MovieLocalDataSource
import com.davidmag.movienatic.data.source.local.dto.GenreDb
import com.davidmag.movienatic.data.source.local.dto.MovieDb
import com.davidmag.movienatic.data.source.local.mapper.GenreLocalMapper
import com.davidmag.movienatic.data.source.local.mapper.MovieLocalMapper
import com.davidmag.movienatic.domain.model.Movie
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.realm.Realm
import io.realm.RealmResults

class MovieLocalDataSourceImpl : MovieLocalDataSource {

    val realm = Realm.getDefaultInstance()

    override fun cache(movies: List<Movie>): Maybe<*> {
        return Maybe.fromCallable {
            Realm.getDefaultInstance().use { r ->
                r.executeTransaction { transaction ->
                    movies.forEach {
                        it.genres?.let {
                            transaction.copyToRealmOrUpdate(GenreLocalMapper.toDto(it))
                        }
                    }
                    transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movies))
                }
            }
        }
    }

    override fun patch(movie: Movie): Maybe<*> {
        return Maybe.fromCallable {
            Realm.getDefaultInstance().use { r ->
                r.executeTransaction { transaction ->
                    transaction.copyToRealmOrUpdate(MovieLocalMapper.toDto(movie))
                }
            }
        }
    }

    override fun get(id : Int?, genreId : Int?) : Flowable<List<Movie>> {
        var query = realm.where(MovieDb::class.java)

        if(id != null){
            query = query.equalTo("id", id)
        }

        return query.findAllAsync().asFlowable().map{
            if(genreId != null){
                it.filter {
                    it.genres.orEmpty().filter {
                        it.id == genreId
                    }.isNotEmpty()
                }
            }
            else {
                it
            }
        }.map {
            MovieLocalMapper.toEntity(it)
        }
    }
}