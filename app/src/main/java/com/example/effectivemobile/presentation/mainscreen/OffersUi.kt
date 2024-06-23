package com.example.effectivemobile.presentation.mainscreen

import android.graphics.drawable.Drawable
import android.os.Parcelable
import com.example.effectivemobile.presentation.common.BaseItem
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

@Parcelize
data class OffersUi(
    override val itemId: String,
    val id: Long,
    val title: String,
    val town: String,
    val price: String,
    val photo: @RawValue Drawable
) : BaseItem, Parcelable
