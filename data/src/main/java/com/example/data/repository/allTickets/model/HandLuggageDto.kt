package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.HandLuggage
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
