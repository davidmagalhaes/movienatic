package com.davidmag.movienatic.infrastructure

import android.app.Application
import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.davidmag.movienatic.BuildConfig
import com.davidmag.movienatic.data.source.local.util.RoomConverters
import com.davidmag.movienatic.infrastructure.di.ApplicationComponent
import com.davidmag.movienatic.infrastructure.di.DaggerApplicationComponent
import com.facebook.stetho.Stetho
import io.reactivex.plugins.RxJavaPlugins
import java.util.*

class App : Application() {

    companion object  {
        val TAG = App::class.java.simpleName

        lateinit var instance : App
            private set

        lateinit var applicationComponent: ApplicationComponent

        val currentLocale = MutableLiveData<Locale>()
    }
    init {
        instance = this
    }

    override fun onCreate() {
        currentLocale.value = Locale.getDefault()
        currentLocale.observeForever {
            Locale.setDefault(it)
        }

        if(BuildConfig.DEBUG) {
            Stetho.initialize(
                Stetho.newInitializerBuilder(this)
                    .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                    .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                    .build()
            )
        }

        RxJavaPlugins.setErrorHandler {
            Log.i(TAG, "RxJava error handled on Global Handler!")
            it.printStackTrace()
        }

        applicationComponent = DaggerApplicationComponent
            .builder()
            .applicationBind(this)
            .build()

        applicationComponent.inject(this)

        super.onCreate()
    }
}
