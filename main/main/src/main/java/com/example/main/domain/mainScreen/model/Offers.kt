package com.example.main.domain.mainScreen.model


data class Offers(
    val id: Long,
    val title: String,
    val town: String,
    val price: Price,
)
