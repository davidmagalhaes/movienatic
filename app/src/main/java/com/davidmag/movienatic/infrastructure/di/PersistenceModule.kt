package com.davidmag.movienatic.infrastructure.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.davidmag.movienatic.data.source.local.LocalDatabase
import com.davidmag.movienatic.data.source.local.dao.ImageConfigsDao
import com.davidmag.movienatic.data.source.local.dao.MovieDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PersistenceModule {

    @Singleton
    @Provides
    fun provideDatabase(
        application: Application
    ) : LocalDatabase {
        return Room.databaseBuilder(
                application,
                LocalDatabase::class.java,
                "appplus.db"
            )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    fun provideMovieDao(
        database: LocalDatabase
    ) : MovieDao = database.getMovieDao()

    @Provides
    fun provideImageConfigsDao(
        database: LocalDatabase
    ) : ImageConfigsDao = database.getImageConfigsDao()

    @Provides
    fun provideGenreDao(
        database: LocalDatabase
    ) = database.getGenreDao()
}