package com.newsapi.api

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.Date

fun getTimestamp(publishedAt: String): String {
    val sdf = SimpleDateFormat(DATE_FORMAT_PATTERN, Locale.ENGLISH)
    val parsedDate = sdf.parse(publishedAt) ?: return ""
    return durationFromNow(parsedDate)
}

private fun durationFromNow(startDate: Date): String {
    var different = System.currentTimeMillis() - startDate.time
    val secondsInMilli: Long = 1000
    val minutesInMilli = secondsInMilli * 60
    val hoursInMilli = minutesInMilli * 60
    val daysInMilli = hoursInMilli * 24
    val elapsedDays = different / daysInMilli
    different %= daysInMilli
    val elapsedHours = different / hoursInMilli
    different %= hoursInMilli
    val elapsedMinutes = different / minutesInMilli
    different %= minutesInMilli
    val elapsedSeconds = different / secondsInMilli
    if (elapsedDays > 0) return "$elapsedDays days ago"
    if (elapsedHours > 0) return "$elapsedHours hours ago"
    if (elapsedMinutes > 0) return "$elapsedMinutes minutes ago"
    if (elapsedSeconds > 0) return "$elapsedSeconds seconds ago"
    return ""
}