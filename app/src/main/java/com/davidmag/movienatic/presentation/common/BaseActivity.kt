package com.davidmag.movienatic.presentation.common

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidmag.movienatic.R
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent
import kotlinx.android.synthetic.main.activity_base.view.*

abstract class BaseActivity : AppCompatActivity(){

    val presentationComponent by lazy {
        DaggerPresentationComponent
            .builder()
            .applicationComponent(App.applicationComponent)
            .build()
    }

    override fun setContentView(layoutResID: Int) {
        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = constraintLayout.activity_content

        layoutInflater.inflate(layoutResID, frameLayout, true)

        super.setContentView(constraintLayout)
    }
}