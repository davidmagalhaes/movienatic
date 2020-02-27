package com.davidmag.movienatic.presentation.activity

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment : Fragment(){

    inline fun <reified T: ViewModel> initViewModel(crossinline factory: () -> T): T = T::class.java.let { clazz ->
        ViewModelProvider(this, object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                if(modelClass == clazz) {
                    @Suppress("UNCHECKED_CAST")
                    return factory() as T
                }
                throw IllegalArgumentException("Unexpected argument: $modelClass")
            }
        }).get(clazz)
    }
}