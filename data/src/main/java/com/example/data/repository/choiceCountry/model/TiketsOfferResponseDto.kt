package com.example.data.repository.choiceCountry.model

import com.example.domain.choiseCountry.model.MainData
import com.squareup.moshi.Json

data class TicketsOfferResponseDto(
    @Json(name = "tickets_offers")
    val ticketsOffers: List<TicketsOfferDto>,
) {
    fun mapToDomain() = MainData(
        listData = ticketsOffers.map { it.mapToDomain() },
    )
}
