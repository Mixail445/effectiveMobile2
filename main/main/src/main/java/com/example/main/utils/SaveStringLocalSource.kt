package com.example.main.utils

import com.example.main.ui.mainscreen.DataForSharedPreferences

interface SaveStringLocalSource {
    fun getId(): DataForSharedPreferences?

    fun setId(dataForSharedPreferences: DataForSharedPreferences)

    fun clearId()
}
