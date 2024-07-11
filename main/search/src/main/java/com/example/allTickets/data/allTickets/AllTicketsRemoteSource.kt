package com.example.allTickets.data.allTickets

import com.example.allTickets.data.allTickets.model.TicketDto

interface AllTicketsRemoteSource {
    suspend fun getAllTickets(): List<TicketDto>
}
