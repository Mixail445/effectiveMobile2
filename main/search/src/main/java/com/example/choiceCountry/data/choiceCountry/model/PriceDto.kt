package com.example.choiceCountry.data.choiceCountry.model

import com.example.choiceCountry.domain.choiseCountry.model.Price

data class PriceDto(
    val value: Long,
) {
    fun mapToDomain() =
        Price(
            value,
        )
}
