package com.example.main.data.mainscreen.model

import com.example.main.domain.mainScreen.model.MainData

data class MainResponseDto(
    val offers: List<OffersDto>,
) {
    fun mapToDomain() =
        MainData(
            listData = offers.map { it.mapToDomain() },
        )
}
