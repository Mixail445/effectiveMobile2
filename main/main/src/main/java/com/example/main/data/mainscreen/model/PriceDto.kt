package com.example.main.data.mainscreen.model

import com.example.main.domain.mainScreen.model.Price

data class PriceDto(
    val value: Long,
) {
    fun mapToEntity() = Price(value = value)
}
