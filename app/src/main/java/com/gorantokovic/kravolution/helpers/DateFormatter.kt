package com.gorantokovic.kravolution.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
    companion object {
        /**
         * Returns furst letter of day name for passed date.
         */
        fun dayInWeak(date: Date): String {
            val dateFormat = SimpleDateFormat("E")
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(date)
                .first()
                .toString()
        }

        fun dayInMonth(date: Date): String {
            val dateFormat = SimpleDateFormat("d")
            dateFormat.timeZone = TimeZone.getDefault()
            return dateFormat.format(date)
        }

        /**
         * Convert unix time to time in format HH:mm (e.g. 05:42).
         */
        fun getTime(timestamp: Long): String {
            val dateFormat = SimpleDateFormat("HH:mm")
            dateFormat.timeZone = TimeZone.getDefault()
            val date = Date(timestamp * 1000)
            return dateFormat.format(date)
                .toString()
        }
    }
}