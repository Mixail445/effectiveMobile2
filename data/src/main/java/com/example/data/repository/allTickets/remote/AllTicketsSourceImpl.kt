package com.example.data.repository.allTickets.remote

import com.example.data.repository.allTickets.model.TicketDto
import com.example.data.repository.mainscreen.remote.DispatchersProvider
import com.example.data.repository.allTickets.AllTicketsRemoteSource
import kotlinx.coroutines.withContext

class AllTicketsSourceImpl(
    private val api: AllTicketsApi,
    private val dispatchersProvider: DispatchersProvider,
) : AllTicketsRemoteSource {

    override suspend fun getAllTickets(): List<TicketDto> = withContext(dispatchersProvider.io) {
        api.getAllTickets().tickets
    }
}
