package com.example.data

import com.example.data.repository.allTickets.AllTicketsRemoteSource
import com.example.domain.allTickets.AllTicketsRepository
import com.example.domain.allTickets.model.Tickets
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject

class AllTicketsRepositoryImpl @Inject constructor(
    private val remoteSource: AllTicketsRemoteSource,
    private val wrapper: ResultWrapper,
) : AllTicketsRepository {
    override suspend fun getAllList(): AppResult<List<Tickets>, Throwable> = wrapper.wrap {
        remoteSource.getAllTickets().map { it.mapToDomain() }
    }
}
