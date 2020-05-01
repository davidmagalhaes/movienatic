package com.davidmag.movienatic.infrastructure

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.infrastructure.di.DaggerApplicationComponent
import io.reactivex.plugins.RxJavaPlugins
import io.realm.Realm
import io.realm.RealmConfiguration
import java.util.*

class App : Application() {

    companion object  {
        val TAG = App::class.java.simpleName

        lateinit var instance : App
            private set

        val currentLocale = MutableLiveData<Locale>()
    }
    init {
        instance = this
    }

    override fun onCreate() {
        Realm.init(this)
        Realm.setDefaultConfiguration(RealmConfiguration.Builder().
            deleteRealmIfMigrationNeeded().build())

        currentLocale.value = Locale.getDefault()
        currentLocale.observeForever {
            Locale.setDefault(it)
        }

        DaggerApplicationComponent.create().inject(this)

        RxJavaPlugins.setErrorHandler {
            Log.i(TAG, "RxJava error handled on Global Handler!")
            it.printStackTrace()
        }

        super.onCreate()
    }
}
