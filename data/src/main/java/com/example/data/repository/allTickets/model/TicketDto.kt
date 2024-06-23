package com.example.data.repository.allTickets.model

import com.example.domain.allTickets.model.Tickets
import com.example.utils.DateUtils
import com.squareup.moshi.Json
import java.time.LocalDateTime

data class TicketDto(
    @Json(name = "id")
    val id: Long,
    @Json(name = "badge")
    val badge: String? = null,
    @Json(name = "price")
    val price: PriceDto,
    @Json(name = "provider_name")
    val providerName: String,
    @Json(name = "company")
    val company: String,
    @Json(name = "departure")
    val departure: DepartureDto,
    @Json(name = "arrival")
    val arrival: ArrivalDto,
    @Json(name = "has_transfer")
    val hasTransfer: Boolean,
    @Json(name = "has_visa_transfer")
    val hasVisaTransfer: Boolean,
    @Json(name = "luggage")
    val luggage: LuggageDto,
    @Json(name = "hand_luggage")
    val handLuggage: HandLuggageDto,
    @Json(name = "is_returnable")
    val isReturnable: Boolean,
    @Json(name = "is_exchangable")
    val isExchangable: Boolean,
) {
    fun mapToDomain() = Tickets(
        id, badge, price.mapToDomain(),
        providerName,
        company,
        DateUtils.parseLocalDateTime(departure.date) ?: LocalDateTime.MIN,
        DateUtils.parseLocalDateTime(arrival.date) ?: LocalDateTime.MIN,
        hasTransfer,
        hasVisaTransfer,
        departure.airport,
        arrival.airport,
        isReturnable,
        isExchangable,
    )
}
