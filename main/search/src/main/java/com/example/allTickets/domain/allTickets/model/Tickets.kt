package com.example.allTickets.domain.allTickets.model

import java.time.LocalDateTime

data class Tickets(
    val id: Long,
    val badge: String? = null,
    val price: Price,
    val providerName: String,
    val company: String,
    val departure: LocalDateTime,
    val arrival: LocalDateTime,
    val hasTransfer: Boolean,
    val hasVisaTransfer: Boolean,
    val luggage: String,
    val handLuggage: String,
    val isReturnable: Boolean,
    val isExchangable: Boolean,
)
