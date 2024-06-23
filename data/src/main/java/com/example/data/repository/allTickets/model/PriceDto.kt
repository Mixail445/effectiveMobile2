package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.Price
import com.squareup.moshi.Json

data class PriceDto(
    @Json(name = "value")
    val value: Long,
) {
    fun mapToDomain() = Price(
        value,
    )
}
