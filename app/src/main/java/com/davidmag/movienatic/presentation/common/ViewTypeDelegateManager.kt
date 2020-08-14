package com.davidmag.movienatic.presentation.common

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ViewTypeDelegateManager(
    private val delegateMap : HashMap<String, ViewTypeDelegate<PresentationObject>>
) {
    private val viewTypeDelegateMap = SparseArray<ViewTypeDelegate<PresentationObject>>()

    init {
        delegateMap.values.forEach {
            viewTypeDelegateMap.append(it.getViewType(), it)
        }
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : RecyclerView.ViewHolder> onCreateViewHolder(inflater: LayoutInflater, parent: ViewGroup, viewType: Int): T {
        return viewTypeDelegateMap[viewType].onCreateViewHolder(inflater, parent, viewType) as T
    }

    @Suppress("UNCHECKED_CAST")
    fun <T : RecyclerView.ViewHolder> onBindViewHolder(item : PresentationObject, holder: T, position: Int) {
        delegateMap[item.javaClass.name]?.onBindViewHolder(holder, position)
    }
}