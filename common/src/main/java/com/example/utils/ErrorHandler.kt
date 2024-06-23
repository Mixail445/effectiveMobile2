package com.example.utils

import android.content.Context
import com.example.common.R
import dagger.hilt.android.qualifiers.ApplicationContext
import java.net.UnknownHostException
import javax.inject.Inject

class ErrorHandel @Inject constructor(
    @ApplicationContext private val context: Context,
) {
    fun handleError(e: Throwable): String {
        when (e) {
            is UnknownHostException -> context.getString(androidx.appcompat.R.string.search_menu_title)
            else -> context.getString(androidx.appcompat.R.string.search_menu_title)
        }
        return e.message.toString()
    }
}
