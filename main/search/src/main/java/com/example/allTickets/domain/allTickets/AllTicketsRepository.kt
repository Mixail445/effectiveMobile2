package com.example.allTickets.domain.allTickets

import com.example.allTickets.domain.allTickets.model.Tickets
import com.example.utils.AppResult

interface AllTicketsRepository {
    suspend fun getAllList(): AppResult<List<Tickets>, Throwable>
}
