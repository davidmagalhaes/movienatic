package com.davidmag.movienatic.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.davidmag.movienatic.R
import kotlinx.android.synthetic.main.activity_base.view.*

abstract class BaseActivity : AppCompatActivity(){
    override fun setContentView(layoutResID: Int) {
        val constraintLayout = layoutInflater.inflate(R.layout.activity_base, null)
        val frameLayout = constraintLayout.activity_content

        layoutInflater.inflate(layoutResID, frameLayout, true)

        super.setContentView(constraintLayout)
    }

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