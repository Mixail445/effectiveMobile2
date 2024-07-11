package com.example.allTickets.data.allTickets.model

import com.example.allTickets.domain.allTickets.model.Luggage
import com.squareup.moshi.Json

data class LuggageDto(
    @Json(name = "has_luggage")
    val hasLuggage: Boolean,
    @Json(name = "price")
    val price: PriceDto? = null,
) {
    fun mapToDomain() =
        Luggage(
            hasLuggage,
            price?.mapToDomain(),
        )
}
