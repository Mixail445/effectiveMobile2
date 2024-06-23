package com.example.effectivemobile.presentation.choiceCountry

import android.content.Context
import android.graphics.Color
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class ChoiceCountryMapper @Inject
constructor(
    @ApplicationContext val context: Context,
) {
    private val list = listOf(Color.RED, Color.BLUE, Color.WHITE)
    fun getColorByIndex(timeRange: List<String>): Int {
        val colors = listOf(Color.RED, Color.BLUE, Color.WHITE)
        val index = timeRange.hashCode() % colors.size
        return colors[index]
    }
    fun formatNumber(number: Long): String {
        return number.toString().reversed().chunked(3).joinToString(" ").reversed()
    }
}
