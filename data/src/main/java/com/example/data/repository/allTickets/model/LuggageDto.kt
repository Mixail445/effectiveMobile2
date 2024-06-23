package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.Luggage
import com.squareup.moshi.Json

data class LuggageDto(
    @Json(name = "has_luggage")
    val hasLuggage: Boolean,
    @Json(name = "price")
    val price: PriceDto? = null,
) {
    fun mapToDomain() = Luggage(
        hasLuggage,
        price?.mapToDomain(),
    )
}
