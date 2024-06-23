package com.example.utils

import java.time.* // ktlint-disable no-wildcard-imports
import java.time.format.DateTimeFormatter

fun LocalDateTime.format(pattern: String): String? {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

fun LocalDate.format(pattern: String): String? {
    val formatter = DateTimeFormatter.ofPattern(pattern)
    return format(formatter)
}

fun LocalDateTime.toEpochMillis() = ZonedDateTime.of(this, ZoneId.systemDefault()).toInstant().toEpochMilli()

object DateUtils {
    private const val CALENDAR_UI_FORMAT = "yyyy/MM/dd"
    const val CALENDAR_UI_ITEM_FORMAT = "yyyy/MM/dd   mm:ss:mm"
    const val UI_ITEM_ALL_TICKETS_FORMAT = "hh:mm"

    fun parseLocalDateTime(date: String?) =
        try {
            LocalDateTime.parse(date, DateTimeFormatter.ISO_DATE_TIME)
        } catch (e: Exception) {
            e
            null
        }

    fun parseLocalDate(date: String) =
        try {
            LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE)
        } catch (e: Exception) {
            e
            null
        }

    fun parseLocalDateTime(date: Long) =
        try {
            LocalDateTime.ofInstant(Instant.ofEpochMilli(date), ZoneId.systemDefault())
        } catch (e: Exception) {
            e
            null
        }

    fun getCalendarUiDate(date: LocalDateTime?): String {
        return if (date == null) {
            ""
        } else {
            date.format(DateTimeFormatter.ofPattern("dd MMMM"))
        }
    }
    fun formatDateString(dateString: String): String {
        val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val outputFormatter = DateTimeFormatter.ofPattern("dd MMMM")

        val localDate = LocalDate.parse(dateString, inputFormatter)
        return localDate.format(outputFormatter)
    }

    fun getCurrentDate() = LocalDateTime.now().format("yyyy-MM-dd")
}