package com.example.data

import com.example.data.repository.choiceCountry.ChoiceCountryRemoteSource
import com.example.domain.choiseCountry.ChoiceCountryRepository
import com.example.domain.choiseCountry.model.TicketsOffer
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject


class ChoiceCountryRepositoryImpl @Inject constructor(
    private val remoteSource: ChoiceCountryRemoteSource,
    private val wrapper: ResultWrapper,
) : ChoiceCountryRepository {
    override suspend fun shortListTickets(): AppResult<List<TicketsOffer>, Throwable> = wrapper.wrap {
        remoteSource.getOffers().mapToDomain().listData
    }
}
