package com.example.choiceCountry.ui

import android.os.Parcelable
import com.example.common.BaseItem
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChoiceCountryUi(
    override val itemId: String,
    val id: Long,
    val title: String,
    val price: String,
    val timeRange: String,
    val viewColor: Int,
) : BaseItem,
    Parcelable

data class TitleTextForRcUi(
    val text: String = "Прямые рейсы",
    override val itemId: String,
) : BaseItem

data class BottomTextForRcUi(
    override val itemId: String,
    val text: String = "Показать все",
) : BaseItem
