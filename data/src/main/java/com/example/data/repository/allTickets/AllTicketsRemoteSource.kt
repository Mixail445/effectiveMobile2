package com.example.data.repository.allTickets

import com.example.data.repository.allTickets.model.TicketDto

interface AllTicketsRemoteSource {
    suspend fun getAllTickets(): List<TicketDto>
}
