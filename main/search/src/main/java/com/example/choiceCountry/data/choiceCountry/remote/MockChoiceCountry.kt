package com.example.choiceCountry.data.choiceCountry.remote

import com.example.choiceCountry.data.choiceCountry.ChoiceCountryRemoteSource
import com.example.choiceCountry.data.choiceCountry.model.PriceDto
import com.example.choiceCountry.data.choiceCountry.model.TicketsOfferDto
import com.example.choiceCountry.data.choiceCountry.model.TicketsOfferResponseDto
import javax.inject.Inject

class MockChoiceCountry
    @Inject
    constructor() : ChoiceCountryRemoteSource {
        override suspend fun getOffers(): TicketsOfferResponseDto =
            TicketsOfferResponseDto(
                listOf(
                    TicketsOfferDto(
                        id = 1,
                        title = "Уральские авиалинии",
                        timeRange =
                            listOf(
                                "07:00",
                                "09:10",
                                "10:00",
                                "11:30",
                                "14:15",
                                "19:10",
                                "21:00",
                                "23:30",
                            ),
                        price = PriceDto(value = 3999),
                    ),
                    TicketsOfferDto(
                        id = 10,
                        title = "Победа",
                        timeRange =
                            listOf(
                                "09:10",
                                "21:00",
                            ),
                        price = PriceDto(value = 4999),
                    ),
                    TicketsOfferDto(
                        id = 30,
                        title = "NordStar",
                        timeRange =
                            listOf(
                                "07:00",
                            ),
                        price = PriceDto(value = 2390),
                    ),
                ),
            )
    }
