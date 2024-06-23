package com.example.data.repository.allTickets.remote

import com.example.data.repository.allTickets.model.TicketResponseDto
import retrofit2.http.GET

interface AllTicketsApi {
    @GET("c0464573-5a13-45c9-89f8-717436748b69")
    suspend fun getAllTickets(): TicketResponseDto
}
