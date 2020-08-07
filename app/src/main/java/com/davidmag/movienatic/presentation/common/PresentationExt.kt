package com.davidmag.movienatic.presentation.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

inline fun <reified T: ViewModel> AppCompatActivity.initViewModel(crossinline factory: () -> T): T =
    _initViewModel(this, intent.extras, factory)

inline fun <reified T: ViewModel> Fragment.initViewModel(crossinline factory: () -> T): T =
    _initViewModel(this, arguments, factory)

inline fun <reified T: ViewModel> _initViewModel(owner: ViewModelStoreOwner, args: Bundle?, crossinline factory: () -> T): T = T::class.java.let { clazz ->
    ViewModelProvider(owner, object: ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if(modelClass == clazz) {
                @Suppress("UNCHECKED_CAST")
                val viewModel = factory() as T
                if(viewModel is BaseViewModel){
                    viewModel.init(args)
                }
                return viewModel
            }
            throw IllegalArgumentException("Unexpected argument: $modelClass")
        }
    }).get(clazz)
}

