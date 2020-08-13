package com.gorantokovic.kravolution.extensions

import org.joda.time.DateTime
import org.joda.time.DateTimeZone
import org.joda.time.LocalDateTime
import java.util.*

/**
 * Shift date for passed number of days.
 * For past pass negative number
 */
fun Date.shiftedForDays(days: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.DATE, days)
    return c.time
}

/**
 * Shift date for passed number of months.
 * For past pass negative number
 */
fun Date.shiftedForMonths(months: Int): Date {
    val c = Calendar.getInstance()
    c.time = this
    c.add(Calendar.MONTH, months)
    return c.time
}

/**
 * Returns unixtime when passed day starts by current timezome
 */
fun Date.dayStartsAt(): Long {
    val localDateTime = LocalDateTime(this)
        .withMillisOfDay(0)
    return localDateTimeToUnixTime(localDateTime)
}

/**
 * Returns unixtime when passed day ends by current timezome
 */
fun Date.dayEndsAt(): Long {
    val localDateTime = LocalDateTime(this)
        .withHourOfDay(23)
        .withMinuteOfHour(59)
        .withSecondOfMinute(59)
        .withMillisOfSecond(999)
    return localDateTimeToUnixTime(localDateTime)
}

private fun localDateTimeToUnixTime(localDateTime: LocalDateTime): Long {
    val utc: DateTime = localDateTime.toDateTime(DateTimeZone.getDefault())
    val secondsSinceEpoch = utc.millis / 1000
    return secondsSinceEpoch
}