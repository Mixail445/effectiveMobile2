package com.example.main.data.mainscreen.remote

import com.example.main.data.mainscreen.MainRemoteSource
import com.example.main.data.mainscreen.model.MainResponseDto
import com.example.main.data.mainscreen.model.OffersDto
import com.example.main.data.mainscreen.model.PriceDto
import javax.inject.Inject

class MockMainRemoteSource
    @Inject
    constructor() : MainRemoteSource {
        override suspend fun getOffers(): MainResponseDto =
            MainResponseDto(
                offers =
                    listOf(
                        OffersDto(
                            id = 1,
                            title = "Die Antwoord",
                            town = "Будапешт",
                            price = PriceDto(value = 5000),
                        ),
                        OffersDto(
                            id = 2,
                            title = "Socrat&Lera",
                            town = "Санкт-Петербург",
                            price = PriceDto(value = 1999),
                        ),
                        OffersDto(
                            id = 3,
                            title = "Лампабикт",
                            town = "Москва",
                            price = PriceDto(value = 2390),
                        ),
                    ),
            )
    }
