package com.example.choiceCountry.data

import com.example.choiceCountry.data.choiceCountry.remote.MockChoiceCountry
import com.example.choiceCountry.domain.choiseCountry.ChoiceCountryRepository
import com.example.choiceCountry.domain.choiseCountry.model.TicketsOffer
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject

class ChoiceCountryRepositoryImpl
    @Inject
    constructor(
        private val remoteSource: MockChoiceCountry,
        private val wrapper: ResultWrapper,
    ) : ChoiceCountryRepository {
        override suspend fun shortListTickets(): AppResult<List<TicketsOffer>, Throwable> =
            wrapper.wrap {
                remoteSource.getOffers().mapToDomain().listData
            }
    }
