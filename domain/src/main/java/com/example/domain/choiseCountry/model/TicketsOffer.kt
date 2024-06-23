package com.example.domain.choiseCountry.model

data class TicketsOffer(
    val id: Long,
    val title: String,
    val timeRange: List<String>,
    val price: Long,
)