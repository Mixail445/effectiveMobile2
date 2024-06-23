package com.example.effectivemobile.presentation.mainscreen.dialog

import com.example.effectivemobile.presentation.common.Screens
import com.example.effectivemobile.presentation.mainscreen.OffersUi

class DialogView {
    data class Model(
        val textTitle: String,
    )

    sealed interface Event {
        data object OnClickGroupOne : Event
        data object OnClickGroupTwo : Event
        data object OnClickGroupThree : Event
        data object OnClickIcHard : Event
        data object OnClickIcAnywhere : Event
        data object OnClickIcWeekend : Event
        data object OnClickIcFire : Event
    }
    sealed interface UiLabel {
        data class ShowSearchScreen(val screen: Screens) : UiLabel
        data class ShowEmptyScreen(val screen: Screens) : UiLabel
    }
}
