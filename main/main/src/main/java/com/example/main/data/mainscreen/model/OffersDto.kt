package com.example.main.data.mainscreen.model

import com.example.main.domain.mainScreen.model.Offers
import com.squareup.moshi.Json

data class OffersDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "title")
    val title: String,
    @Json(name = "town")
    val town: String,
    @Json(name = "price")
    val price: PriceDto,
) {
    fun mapToDomain() =
        Offers(
            id = id,
            title = title,
            town = town,
            price = price.mapToEntity(),
        )
}
