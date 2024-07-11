package com.example.choiceCountry.data.choiceCountry.remote

import com.example.choiceCountry.data.choiceCountry.ChoiceCountryRemoteSource
import com.example.choiceCountry.data.choiceCountry.model.TicketsOfferResponseDto
import com.example.common.DispatchersProvider
import kotlinx.coroutines.withContext

class ChoiceCountrySourceImpl(
    private val api: ChoiceCountryApi,
    private val dispatchersProvider: DispatchersProvider,
) : ChoiceCountryRemoteSource {
    override suspend fun getOffers(): TicketsOfferResponseDto =
        withContext(dispatchersProvider.io) {
            api.getShortListTickets()
        }
}
