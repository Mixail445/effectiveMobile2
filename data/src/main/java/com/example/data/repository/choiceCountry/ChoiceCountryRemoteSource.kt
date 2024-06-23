package com.example.data.repository.choiceCountry

import com.example.data.repository.choiceCountry.model.TicketsOfferResponseDto


interface ChoiceCountryRemoteSource {
    suspend fun getOffers(): TicketsOfferResponseDto
}
