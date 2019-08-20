package com.davidmag.movienatic

import android.app.Application
import android.content.Context
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.davidmag.movienatic.rest.configuration.ConfigurationsResourceClient
import com.davidmag.movienatic.util.Constants
import com.facebook.stetho.Stetho
import com.uphyca.stetho_realm.RealmInspectorModulesProvider
import io.realm.Realm
import io.realm.RealmConfiguration
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.GsonBuilder
import okhttp3.ConnectionPool
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import android.R.attr.typeface
import android.graphics.Typeface
import android.content.res.AssetManager




class App : Application() {

    companion object  {
        private lateinit var retrofit : Retrofit
        private lateinit var singleton : App

        val currentLocale = MutableLiveData<Locale>()

        fun retrofit() : Retrofit {
            return retrofit
        }
        fun getApp() : App {
            return singleton
        }
    }

    init {
        singleton = this
    }

    override fun onCreate() {
        Realm.init(this)

        val mRealmConfiguration = RealmConfiguration.Builder()
            .name("database.realm")
            .schemaVersion(3) // skip if you are not managing
            .deleteRealmIfMigrationNeeded()
            .build()

        //Realm.deleteRealm(mRealmConfiguration) //Remover essa linha depois

        Realm.getInstance(mRealmConfiguration)
        Realm.setDefaultConfiguration(mRealmConfiguration)

        currentLocale.postValue(Locale.getDefault())
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
        val gson = GsonBuilder().setDateFormat("yyyy-MM-dd").create()

        AppGlideModule.factory = OkHttpUrlLoader.Factory(okHttpClient)

        retrofit = Retrofit.Builder().
            baseUrl(BuildConfig.SERVER_URL).
            client(okHttpClient).
            addConverterFactory(GsonConverterFactory.create(gson)).
            build()

        super.onCreate()

        ConfigurationsResourceClient.updateConfigurations()
    }
}