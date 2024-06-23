package com.example.domain.mainScreen

import com.example.domain.mainScreen.model.MainData
import com.example.utils.AppResult

interface MainScreenRepository {
    suspend fun getOffersRemote(): AppResult<MainData?, Throwable>
}
