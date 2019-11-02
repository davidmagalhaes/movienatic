package com.davidmag.movienatic.infrastructure

import android.app.Application
import androidx.lifecycle.MutableLiveData
import java.util.*

class App : Application() {
    companion object  {
        lateinit var instance : App
            private set

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

        super.onCreate()
    }
}