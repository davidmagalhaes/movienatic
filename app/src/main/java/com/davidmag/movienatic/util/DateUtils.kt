package com.davidmag.movienatic.util

import com.davidmag.movienatic.App
import com.davidmag.movienatic.R
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    lateinit var dateWordsFormatter : SimpleDateFormat

    init {
        App.currentLocale.observeForever{
            dateWordsFormatter = SimpleDateFormat(App.instance.getString(R.string.format_date_words), it)
        }
    }

    fun dateWordsFormat(date : Date) : String {
        return dateWordsFormatter.format(date)
    }
}