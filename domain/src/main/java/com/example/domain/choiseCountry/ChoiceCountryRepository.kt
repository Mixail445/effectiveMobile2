package com.example.domain.choiseCountry

import com.example.domain.choiseCountry.model.TicketsOffer
import com.example.utils.AppResult

interface ChoiceCountryRepository {
    suspend fun shortListTickets(): AppResult<List<TicketsOffer>, Throwable>
}
