package com.example.allTickets.ui

import com.example.navigation.Screens

class AllTicketsView {
    data class Model(
        val isLoading: Boolean = false,
        val allTicketsItem: List<AllTicketsUi?> = emptyList(),
        val title: String = "",
        val bottomTitle: String = "",
    )

    sealed interface Event {
        data object OnClickBack : Event
    }

    sealed interface UiLabel {
        data class ShowBackScreen(
            val screen: Screens,
        ) : UiLabel
    }
}
