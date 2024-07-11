package com.example.main.data.mainscreen.remote

import com.example.main.data.mainscreen.model.MainResponseDto
import retrofit2.http.GET

interface MainScreenApi {
    @GET("094958d7-804b-4af9-a047-8411dd91c667")
    suspend fun getOffers(): MainResponseDto
}
