package com.example.data.repository.allTickets.model

import com.squareup.moshi.Json

data class TicketResponseDto(
    @Json(name = "tickets") var tickets: List<TicketDto>,
)
