package com.example.domain.allTickets

import com.example.domain.allTickets.model.Tickets
import com.example.utils.AppResult

interface AllTicketsRepository {
    suspend fun getAllList(): AppResult<List<Tickets>, Throwable>
}
