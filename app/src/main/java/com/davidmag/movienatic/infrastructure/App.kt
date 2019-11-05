package com.davidmag.movienatic.infrastructure

import android.app.Activity
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.infrastructure.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.RealmMigration
import java.util.*
import javax.inject.Inject

class App : Application() {


    companion object  {
        lateinit var instance : App
            private set

        val currentLocale = MutableLiveData<Locale>()
    }

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

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

        super.onCreate()
    }
}
