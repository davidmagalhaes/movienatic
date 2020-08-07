package com.davidmag.movienatic.presentation.common

import android.os.Bundle
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {
    open fun init(args: Bundle?){}
}