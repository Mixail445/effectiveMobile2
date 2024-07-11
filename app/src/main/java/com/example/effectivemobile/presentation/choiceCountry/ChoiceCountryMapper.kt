package com.example.effectivemobile.presentation.choiceCountry

import android.content.Context
import android.graphics.Color
import javax.inject.Inject

class ChoiceCountryMapper
    @Inject
    constructor(
        val context: Context,
    ) {
        private val list = listOf(Color.RED, Color.BLUE, Color.WHITE)

        fun getColorByIndex(timeRange: List<String>): Int {
            val colors = listOf(Color.RED, Color.BLUE, Color.WHITE)
            val index = timeRange.hashCode() % colors.size
            return colors[index]
        }

        fun formatNumber(number: Long): String =
            number
                .toString()
                .reversed()
                .chunked(3)
                .joinToString(" ")
                .reversed()
    }
