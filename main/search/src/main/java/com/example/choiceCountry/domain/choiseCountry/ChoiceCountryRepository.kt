package com.example.choiceCountry.domain.choiseCountry

import com.example.choiceCountry.domain.choiseCountry.model.TicketsOffer
import com.example.utils.AppResult

interface ChoiceCountryRepository {
    suspend fun shortListTickets(): AppResult<List<TicketsOffer>, Throwable>
}
