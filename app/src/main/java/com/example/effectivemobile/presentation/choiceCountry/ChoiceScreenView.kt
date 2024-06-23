package com.example.effectivemobile.presentation.choiceCountry

import com.example.effectivemobile.presentation.common.BaseItem
import com.example.effectivemobile.presentation.common.Screens

class ChoiceScreenView {
    data class Model(
        val isLoading: Boolean = false,
        val shortList: List<BaseItem?> = emptyList(),
        val textCityTop: String = "",
        val textCityBottom: String = "",
        val bottomTextDateOtp: String = "",
        val bottomTextDatePrb: String = "",
    )
    sealed interface Event {
        data object OnClickLookAllTickets : Event
        data object OnCalendarClick : Event
        data class OnUsedSelectDate(val date: Long?) : Event
        data object OnClickChangeIv : Event
    }
    sealed interface UiLabel {
        data class ShowDatePicker(val date: Long?) : UiLabel
        data class ShowScreenAllTickets(val screen: Screens) : UiLabel
    }
}
