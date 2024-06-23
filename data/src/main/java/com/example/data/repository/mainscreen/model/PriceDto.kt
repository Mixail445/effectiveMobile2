package com.example.data.repository.mainscreen.model

import com.example.domain.mainScreen.model.Price


data class PriceDto(val value: Long) {
    fun mapToEntity() = Price(value = value)
}
