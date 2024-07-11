package com.example.choiceCountry.ui

import com.example.common.BaseItem

interface ChoiceCountryAllData {
    data class State(
        val isLoading: Boolean = false,
        val shortTickets: List<ChoiceCountryUi> = emptyList(),
        val topTitleForRc: List<TitleTextForRcUi> = emptyList(),
        val bottomTextForRcUi: List<BottomTextForRcUi> = emptyList(),
        val textCityTop: String,
        val textCityBottom: String,
        val bottomTextDateOtp: String = "",
        val bottomTextDatePrb: String = "",
    ) {
        fun mapToUi(): ChoiceScreenView.Model =
            ChoiceScreenView.Model(
                isLoading = isLoading,
                shortList = mapToItems(),
                textCityTop = textCityTop,
                textCityBottom = textCityBottom,
                bottomTextDateOtp = bottomTextDateOtp,
                bottomTextDatePrb = bottomTextDatePrb,
            )

        private fun mapToItems(): List<BaseItem> {
            val items = mutableListOf<BaseItem>()
            items += topTitleForRc
            items += shortTickets
            items += bottomTextForRcUi
            return items
        }
    }
}
