package com.davidmag.movienatic.presentation.dto

import com.davidmag.movienatic.presentation.common.PresentationObject

data class MoviePresentation(
    override val viewType: Int,
    val id: Long = -1,
    val title: String? = null,
    val posterPath: String? = null,
    val backdropPath: String? = null,
    val overview: String? = null,
    override val exception: Throwable? = null
) : PresentationObject {
    companion object {
        const val VIEWTYPE_ERROR_CONNECTION = 1
    }
}