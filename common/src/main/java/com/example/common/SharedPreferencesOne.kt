package com.example.common

interface SharedPreferencesOne<T> {
    fun getData(key: String): T?

    fun clearData()

    fun setData(
        key: String,
        data: T,
    )
}
