package com.example.choiceCountry.data.choiceCountry.model

import com.example.choiceCountry.domain.choiseCountry.model.MainData
import com.squareup.moshi.Json

data class TicketsOfferResponseDto(
    @Json(name = "tickets_offers")
    val ticketsOffers: List<TicketsOfferDto>,
) {
    fun mapToDomain() =
        MainData(
            listData = ticketsOffers.map { it.mapToDomain() },
        )
}
