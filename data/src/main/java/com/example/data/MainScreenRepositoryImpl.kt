package com.example.data

import com.example.data.repository.mainscreen.MainRemoteSource
import com.example.domain.mainScreen.MainScreenRepository
import com.example.domain.mainScreen.model.MainData
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject

class MainScreenRepositoryImpl @Inject constructor(
    private val remoteSource: MainRemoteSource,
    private val wrapper: ResultWrapper,
) : MainScreenRepository {
    override suspend fun getOffersRemote(): AppResult<MainData?, Throwable> = wrapper.wrap {
        remoteSource.getOffers().mapToDomain()
    }
}
