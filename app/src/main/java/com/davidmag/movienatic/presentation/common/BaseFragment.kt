package com.davidmag.movienatic.presentation.common

import androidx.fragment.app.Fragment
import com.davidmag.movienatic.infrastructure.App
import com.davidmag.movienatic.presentation.di.DaggerPresentationComponent

abstract class BaseFragment : Fragment(){
    val presentationComponent by lazy {
        DaggerPresentationComponent
            .builder()
            .applicationComponent(App.applicationComponent)
            .build()
    }
}