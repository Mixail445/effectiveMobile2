package com.example.data.repository.choiceCountry.remote

import com.example.data.repository.choiceCountry.model.TicketsOfferResponseDto
import com.example.data.repository.mainscreen.remote.DispatchersProvider
import com.example.data.repository.choiceCountry.ChoiceCountryRemoteSource
import kotlinx.coroutines.withContext

class ChoiceCountrySourceImpl(
    private val api: ChoiceCountryApi,
    private val dispatchersProvider: DispatchersProvider,
) : ChoiceCountryRemoteSource {
    override suspend fun getOffers(): TicketsOfferResponseDto = withContext(dispatchersProvider.io) {
        api.getShortListTickets()
    }
}
