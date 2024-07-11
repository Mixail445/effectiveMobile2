package com.example.main.data.mainscreen

import com.example.main.data.mainscreen.model.MainResponseDto


interface MainRemoteSource {
    suspend fun getOffers(): MainResponseDto
}
