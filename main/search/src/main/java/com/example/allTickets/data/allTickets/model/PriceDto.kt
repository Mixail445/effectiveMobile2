package com.example.allTickets.data.allTickets.model

import com.example.allTickets.domain.allTickets.model.Price
import com.squareup.moshi.Json

data class PriceDto(
    @Json(name = "value")
    val value: Long,
) {
    fun mapToDomain() =
        Price(
            value,
        )
}
