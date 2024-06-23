package com.example.data.repository.mainscreen

import com.example.data.repository.mainscreen.model.MainResponseDto


interface MainRemoteSource {
    suspend fun getOffers(): MainResponseDto
}
