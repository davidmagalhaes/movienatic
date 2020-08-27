package com.davidmag.movienatic.presentation.di

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

import com.bumptech.glide.module.AppGlideModule
import com.davidmag.movienatic.infrastructure.App

private val presentationComponent : PresentationComponent by lazy {
    DaggerPresentationComponent
        .builder()
        .applicationComponent(App.applicationComponent)
        .build()
}

val AppCompatActivity.presentationComponent by lazy {
    presentationComponent
}

val Fragment.presentationComponent by lazy {
    presentationComponent
}

val AppGlideModule.presentationComponent by lazy {
    presentationComponent
}

