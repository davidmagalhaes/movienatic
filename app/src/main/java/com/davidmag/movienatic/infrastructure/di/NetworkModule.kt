package com.davidmag.movienatic.infrastructure.di

import br.com.softbuilder.patronuskiosk.infrastructure.util.GsonDateTimeTypeAdapter
import br.com.softbuilder.patronuskiosk.infrastructure.util.GsonSimpleDateTypeAdapter
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.AppGlideModule
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.remote.api.ConfigurationsApi
import com.davidmag.movienatic.data.source.remote.api.MovieApi
import com.facebook.stetho.Stetho
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import org.threeten.bp.LocalDate
import org.threeten.bp.OffsetDateTime
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    val okHttpClient by lazy {
        val okHttp3ClientBuilder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(10, 1, TimeUnit.MINUTES))
            .readTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .connectTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)
            .writeTimeout(BuildConfig.NETWORK_TIMEOUT, TimeUnit.MILLISECONDS)


        if(BuildConfig.DEBUG){
            Stetho.initialize(
                Stetho.newInitializerBuilder(App.instance)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(App.instance))
                    .build()
            )

            okHttp3ClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        val client = okHttp3ClientBuilder.build()

        AppGlideModule.factory = OkHttpUrlLoader.Factory(client)

        client
    }

    val gson by lazy {
        val gsonBuilder = GsonBuilder().setDateFormat("yyyy-MM-dd")

        gsonBuilder.registerTypeAdapter(OffsetDateTime::class.java, GsonDateTimeTypeAdapter)
        gsonBuilder.registerTypeAdapter(LocalDate::class.java, GsonSimpleDateTypeAdapter)

        gsonBuilder.create()
    }

    val retrofit by lazy {
        Retrofit.Builder().
            baseUrl(BuildConfig.SERVER_URL).
            client(okHttpClient).
            addConverterFactory(GsonConverterFactory.create(gson)).
            build()
    }

    @Singleton
    @Provides
    fun provideMovieApi() : MovieApi {
        return retrofit.create(MovieApi::class.java)
    }

    @Singleton
    @Provides
    fun provideConfigurationsApi() : ConfigurationsApi {
        return retrofit.create(ConfigurationsApi::class.java)
    }
}