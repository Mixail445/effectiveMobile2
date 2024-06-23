package com.example.effectivemobile.presentation.mainscreen

import android.os.Parcelable
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize
@JsonClass(generateAdapter = true)
@Parcelize
data class SaveDataInSharedPref(val text: String) : Parcelable
