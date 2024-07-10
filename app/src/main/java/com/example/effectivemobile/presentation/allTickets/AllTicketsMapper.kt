package com.example.effectivemobile.presentation.allTickets

import android.content.Context
import java.text.DecimalFormat
import java.time.Duration
import java.time.LocalDateTime
import javax.inject.Inject

class
AllTicketsMapper@Inject
    constructor(
        val context: Context,
    ) {
        fun formatNumber(number: Long): String =
            number
                .toString()
                .reversed()
                .chunked(3)
                .joinToString(" ")
                .reversed()

        fun calculateTimeDifference(
            start: LocalDateTime,
            end: LocalDateTime,
        ): String {
            val duration = Duration.between(start, end)
            val hours = duration.toHours()
            val minutes = duration.toMinutes() % 60
            val df = DecimalFormat("#.##")
            val formattedHours = df.format(hours + minutes / 60.0)
            return if (formattedHours.contains(".")) {
                "${formattedHours}ч"
            } else {
                "${hours.toInt()} час${if (hours.toInt() > 1) "ов" else ""}"
            }
        }
    }
