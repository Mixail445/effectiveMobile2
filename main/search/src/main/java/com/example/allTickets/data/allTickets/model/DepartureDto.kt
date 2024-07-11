package com.example.allTickets.data.allTickets.model

import com.example.allTickets.domain.allTickets.model.Departure

data class DepartureDto(
    val town: String,
    val date: String,
    val airport: String,
) {
    fun mapToDomain() =
        Departure(
            town,
            date,
            airport,
        )
}
