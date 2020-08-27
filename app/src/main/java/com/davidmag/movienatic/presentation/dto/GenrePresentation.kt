package com.davidmag.movienatic.presentation.dto

import com.davidmag.movienatic.presentation.common.PresentationObject

data class GenrePresentation(
    override val viewType: Int,
    val id: Long = -1,
    val description: String? = null,
    override val exception: Throwable? = null
): PresentationObject {
    companion object {

    }
}