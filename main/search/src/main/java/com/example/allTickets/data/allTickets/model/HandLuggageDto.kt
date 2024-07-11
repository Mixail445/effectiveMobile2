package com.example.allTickets.data.allTickets.model

import com.example.allTickets.domain.allTickets.model.HandLuggage
import com.squareup.moshi.Json

data class HandLuggageDto(
    @Json(name = "has_hand_luggage")
    val hasHandLuggage: Boolean,
    val size: String? = null,
) {
    fun mapToDomain() =
        HandLuggage(
            hasHandLuggage,
            size,
        )
}
