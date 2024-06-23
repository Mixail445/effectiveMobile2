package com.example.data.repository.mainscreen.model

import com.example.domain.mainScreen.model.MainData

data class MainResponseDto(
    val offers: List<OffersDto>,
) {
    fun mapToDomain() = MainData(
        listData = offers.map { it.mapToDomain() },
    )
}
