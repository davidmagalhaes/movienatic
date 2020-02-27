package com.davidmag.movienatic.presentation.adapter

import android.os.Bundle
import androidx.fragment.app.Fragment

data class TabInfo (
    val title : String,
    val fragment : Fragment,
    val args : Bundle? = null
)