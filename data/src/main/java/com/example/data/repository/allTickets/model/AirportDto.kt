package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.Arrival
import com.squareup.moshi.Json

data class ArrivalDto(
    @Json(name = "town") var town: String,
    @Json(name = "date") var date: String,
    @Json(name = "airport") var airport: String,
) {
    fun mapToDomain() =
        Arrival(town, date, airport)
}
