package com.davidmag.movienatic

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.davidmag.movienatic.repository.ConfigurationsRepository
import com.davidmag.movienatic.util.Constants
import com.davidmag.movienatic.util.RealmDeserializerHelper
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import io.realm.RealmModel
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit


class App : Application() {

    companion object  {
        lateinit var retrofit : Retrofit
            private set
        lateinit var instance : App
            private set

        val currentLocale = MutableLiveData<Locale>()
    }

    init {
        instance = this
    }

    override fun onCreate() {
        Realm.init(this)

        val mRealmConfiguration = RealmConfiguration.Builder()
            .name("database.realm")
            .schemaVersion(3)
            .deleteRealmIfMigrationNeeded()
            .build()

        Realm.getInstance(mRealmConfiguration)
        Realm.setDefaultConfiguration(mRealmConfiguration)

        currentLocale.value = Locale.getDefault()
        currentLocale.observeForever {
            Locale.setDefault(it)
        }

        val okHttp3ClientBuilder = OkHttpClient.Builder()
            .connectionPool(ConnectionPool(10, 1, TimeUnit.MINUTES))
            .readTimeout(Constants.NETWORK_READ_TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(Constants.NETWORK_CONNECT_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(Constants.NETWORK_WRITE_TIMEOUT, TimeUnit.SECONDS)


        if(BuildConfig.DEBUG){
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(
                        RealmInspectorModulesProvider.builder(this).
                            withMetaTables().
                            withLimit(1000).
                            withDeleteIfMigrationNeeded(true).
                            build()
                    )
                    .build()
            )

            okHttp3ClientBuilder.addNetworkInterceptor(StethoInterceptor())
        }

        val okHttpClient = okHttp3ClientBuilder.build()
        val gson = GsonBuilder().
            setDateFormat("yyyy-MM-dd").
            registerTypeAdapter(RealmModel::class.java, RealmDeserializerHelper()).
            create()

        AppGlideModule.factory = OkHttpUrlLoader.Factory(okHttpClient)

        retrofit = Retrofit.Builder().
            baseUrl(BuildConfig.SERVER_URL).
            client(okHttpClient).
            addConverterFactory(GsonConverterFactory.create(gson)).
            build()

        super.onCreate()

        ConfigurationsRepository.getImageConfigurations(true)
    }
}