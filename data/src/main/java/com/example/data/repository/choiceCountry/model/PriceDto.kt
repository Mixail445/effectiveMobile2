package com.example.data.repository.choiceCountry.model

import com.example.domain.choiseCountry.model.Price

data class PriceDto(
    val value: Long,
) {
    fun mapToDomain() = Price(
        value,
    )
}
