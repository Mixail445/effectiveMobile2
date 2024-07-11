package com.example.choiceCountry.data.choiceCountry

import com.example.choiceCountry.data.choiceCountry.model.TicketsOfferResponseDto


interface ChoiceCountryRemoteSource {
    suspend fun getOffers(): TicketsOfferResponseDto
}
