package com.example.main.utils

import com.example.common.SharedPreferencesOne
import com.example.main.ui.mainscreen.DataForSharedPreferences
import com.squareup.moshi.JsonDataException
import javax.inject.Inject

class SaveStringLocalSourceImpl
    @Inject
    constructor(
        private val sharedPreferencesOne: SharedPreferencesOne<DataForSharedPreferences>,
    ) : SaveStringLocalSource {
        override fun getId(): DataForSharedPreferences? =
            try {
                sharedPreferencesOne.getData("key")
            } catch (e: JsonDataException) {
                null
            }

        override fun setId(dataForSharedPreferences: DataForSharedPreferences) {
            sharedPreferencesOne.setData("key", dataForSharedPreferences)
        }

        override fun clearId() {
            sharedPreferencesOne.clearData()
        }
    }
