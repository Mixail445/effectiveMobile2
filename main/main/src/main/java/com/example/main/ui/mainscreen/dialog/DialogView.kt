package com.example.main.ui.mainscreen.dialog

import com.example.navigation.Screens

class DialogView {
    data class Model(
        val textTitle: String,
        val textBottom: String,
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
        data class ShowSearchScreen(
            val screen: Screens,
            val sendBottomText: String,
            val sendTopText: String,
        ) : UiLabel

        data class ShowEmptyScreen(
            val screen: Screens,
        ) : UiLabel
    }
}
