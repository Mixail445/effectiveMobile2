package com.example.data.repository.choiceCountry.remote

import com.example.data.repository.choiceCountry.model.TicketsOfferResponseDto
import retrofit2.http.GET

interface ChoiceCountryApi {
    @GET("38b5205d-1a3d-4c2f-9d77-2f9d1ef01a4a")
    suspend fun getShortListTickets(): TicketsOfferResponseDto
}
