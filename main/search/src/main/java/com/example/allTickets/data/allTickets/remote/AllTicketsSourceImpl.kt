package com.example.allTickets.data.allTickets.remote

import com.example.allTickets.data.allTickets.AllTicketsRemoteSource
import com.example.allTickets.data.allTickets.model.TicketDto
import com.example.common.DispatchersProvider
import kotlinx.coroutines.withContext

class AllTicketsSourceImpl(
    private val api: AllTicketsApi,
    private val dispatchersProvider: DispatchersProvider,
) : AllTicketsRemoteSource {
    override suspend fun getAllTickets(): List<TicketDto> =
        withContext(dispatchersProvider.io) {
            api.getAllTickets().tickets
        }
}
