package com.example.main.data

import com.example.main.data.mainscreen.remote.MockMainRemoteSource
import com.example.main.domain.mainScreen.MainScreenRepository
import com.example.main.domain.mainScreen.model.MainData
import com.example.utils.AppResult
import com.example.utils.ResultWrapper
import javax.inject.Inject

class MainScreenRepositoryImpl
    @Inject
    constructor(
        private val remoteSource: MockMainRemoteSource,
        private val wrapper: ResultWrapper,
    ) : MainScreenRepository {
        override suspend fun getOffersRemote(): AppResult<MainData?, Throwable> =
            wrapper.wrap {
                remoteSource.getOffers().mapToDomain()
            }
    }
