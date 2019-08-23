package com.davidmag.movienatic.util

import com.davidmag.movienatic.App
import com.davidmag.movienatic.R
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    private lateinit var dateWordsFormatter : SimpleDateFormat

    private val isoDateFormatter by lazy {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", App.currentLocale.value!!)
    }

    init {
        App.currentLocale.observeForever{
            dateWordsFormatter = SimpleDateFormat(App.instance.getString(R.string.format_date_words), it)
        }
    }

    fun dateWordsFormat(date : Date) : String {
        return dateWordsFormatter.format(date)
    }

    fun toIsoString(date : Date?) : String?{
        return date?.let { isoDateFormatter.format(it) }
    }

    fun fromIsoString(isoString : String?) : Date? {
        return isoString?.let { isoDateFormatter.parse(it) }
    }
}