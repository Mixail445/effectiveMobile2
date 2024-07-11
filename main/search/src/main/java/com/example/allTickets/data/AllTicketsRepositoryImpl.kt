package com.example.allTickets.data

import com.example.allTickets.data.allTickets.remote.MockAllTickets
import com.example.allTickets.domain.allTickets.AllTicketsRepository
import com.example.allTickets.domain.allTickets.model.Tickets
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject

class AllTicketsRepositoryImpl
    @Inject
    constructor(
        private val remoteSource: MockAllTickets,
        private val wrapper: ResultWrapper,
    ) : AllTicketsRepository {
        override suspend fun getAllList(): AppResult<List<Tickets>, Throwable> =
            wrapper.wrap {
                remoteSource.getAllTickets().map { it.mapToDomain() }
            }
    }
