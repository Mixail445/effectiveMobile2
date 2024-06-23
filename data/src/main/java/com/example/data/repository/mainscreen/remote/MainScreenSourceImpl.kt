package com.example.data.repository.mainscreen.remote

import com.example.data.repository.mainscreen.model.MainResponseDto
import com.example.data.repository.mainscreen.MainRemoteSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class MainScreenSourceImpl(
    private val api: MainScreenApi,
    private val dispatchersProvider: DispatchersProvider,
) : MainRemoteSource {
    override suspend fun getOffers(): MainResponseDto = withContext(dispatchersProvider.io) {
        api.getOffers()
    }
}

interface DispatchersProvider {
    val default: CoroutineDispatcher
    val main: CoroutineDispatcher
    val unconfined: CoroutineDispatcher
    val io: CoroutineDispatcher
}

object DispatchersProviderImpl : DispatchersProvider {
    override val default = Dispatchers.Default
    override val main = Dispatchers.Main.immediate
    override val unconfined = Dispatchers.Unconfined
    override val io = Dispatchers.IO
}
