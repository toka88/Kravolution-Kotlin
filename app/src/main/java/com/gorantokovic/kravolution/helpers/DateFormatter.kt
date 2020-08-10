package com.gorantokovic.kravolution.helpers

import java.text.SimpleDateFormat
import java.util.*

class DateFormatter {
    companion object {
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
    }
}