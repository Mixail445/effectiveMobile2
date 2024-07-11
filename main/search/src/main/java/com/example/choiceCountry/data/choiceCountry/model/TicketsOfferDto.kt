package com.example.choiceCountry.data.choiceCountry.model

import com.example.choiceCountry.domain.choiseCountry.model.TicketsOffer
import com.squareup.moshi.Json

data class TicketsOfferDto(
    val id: Long,
    val title: String,
    @Json(name = "time_range")
    val timeRange: List<String>,
    val price: PriceDto,
) {
    fun mapToDomain() =
        TicketsOffer(
            id,
            title,
            timeRange,
            price.mapToDomain().value,
        )
}
