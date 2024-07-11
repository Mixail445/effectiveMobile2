package com.example.allTickets.data.allTickets.model

import com.squareup.moshi.Json

data class TicketResponseDto(
    @Json(name = "tickets") var tickets: List<TicketDto>,
)
