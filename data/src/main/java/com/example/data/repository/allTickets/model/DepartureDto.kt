package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.Departure

data class DepartureDto(
    val town: String,
    val date: String,
    val airport: String,
) {
    fun mapToDomain() = Departure(
        town,
        date,
        airport,
    )
}
