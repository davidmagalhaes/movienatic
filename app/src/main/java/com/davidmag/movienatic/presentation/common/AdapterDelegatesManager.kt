package com.davidmag.movienatic.presentation.common

import android.util.SparseArray
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView

class AdapterDelegatesManager(
    adapterDelegateMap : Map<Class<out Any>, GroupedAdapterDelegates>
) {
    companion object {
        const val SHIFT_VALUE = 100
    }

    private val adapterDelegates =
        SparseArray<AdapterDelegate<RecyclerView.ViewHolder>>()

    private val classIdMap = HashMap<Int, Class<*>>()
    private val reversedClassIdMap = HashMap<Class<*>, Int>()
    private var classId = 1

    init {
        adapterDelegateMap.forEach { entry ->
            entry.value.forEach {
                @Suppress("UNCHECKED_CAST")
                addDelegate(entry.key, it as AdapterDelegate<RecyclerView.ViewHolder>)
            }
        }
    }

    fun addDelegate(
        clazz: Class<*>,
        adapterDelegate: AdapterDelegate<RecyclerView.ViewHolder>
    ) {
        val shiftedClassId =  reversedClassIdMap[clazz] ?: run {
            val generatedId = classId * SHIFT_VALUE

            classIdMap[generatedId] = clazz
            reversedClassIdMap[clazz] = generatedId

            classId++
            generatedId
        }

        adapterDelegate.supportedViewTypes.forEach {
            adapterDelegates.put(shiftedClassId + it, adapterDelegate)
        }
    }

    fun onCreateViewHolder(
        inflater: LayoutInflater,
        parent : ViewGroup,
        compositeViewType : Int,
        extras : Map<String, Any?>
    ) : RecyclerView.ViewHolder{
        val adapterDelegate = adapterDelegates[compositeViewType] ?:
            reversedClassIdMap[PresentationObject::class.java]?.let {
                //Falls back to PresentationObject AdapterDelegates
                adapterDelegates[it + (compositeViewType % SHIFT_VALUE)]
            }

        return adapterDelegate?.onCreateViewHolder(
            inflater,
            parent,
            compositeViewType % SHIFT_VALUE,
            extras
        ) ?: error("AdapterDelegate not found for creating viewholder with composite viewtype $compositeViewType")
    }

    fun onBindViewHolder(
        supportFragmentManager: FragmentManager,
        items: List<PresentationObject>,
        position : Int,
        viewHolder : RecyclerView.ViewHolder,
        extras : Map<String, Any?>
    ) {
        return adapterDelegates[viewHolder.itemViewType]?.onBindViewHolder(
            supportFragmentManager,
            items,
            viewHolder,
            position,
            extras
        ) ?: run {
            val item = items[position]
            error("AdapterDelegate not found for class ${item::class.qualifiedName} with viewType ${item.viewType}")
        }
    }

    fun getItemCompositeViewType(item : PresentationObject) : Int {
        return item.viewType + (
            reversedClassIdMap[item::class.java] ?:
            reversedClassIdMap[PresentationObject::class.java] ?:
            error("Class ${item::class.java.name} is not mapped on this AdapterDelegatesManager")
        )
    }
}