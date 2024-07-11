package com.example.choiceCountry.domain.choiseCountry.model

data class TicketsOffer(
    val id: Long,
    val title: String,
    val timeRange: List<String>,
    val price: Long,
)