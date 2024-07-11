package com.example.main.domain.mainScreen

import com.example.main.domain.mainScreen.model.MainData
import com.example.utils.AppResult

interface MainScreenRepository {
    suspend fun getOffersRemote(): AppResult<MainData?, Throwable>
}
