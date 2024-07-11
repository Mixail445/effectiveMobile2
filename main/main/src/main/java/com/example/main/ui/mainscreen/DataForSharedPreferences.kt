package com.example.main.ui.mainscreen

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class DataForSharedPreferences(
    @Json(name = "list") val item: String,
) : Parcelable
