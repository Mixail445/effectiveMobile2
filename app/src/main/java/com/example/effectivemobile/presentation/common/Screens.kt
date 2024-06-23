package com.example.effectivemobile.presentation.common

sealed class Screens {
    data object EmptyScreen : Screens()
    data object MainScreen : Screens()
    data class DialogSearch(val title: String) : Screens()
    data class ChoiceCountry(val title: String, val bottomTitle: String) : Screens()
    data object FilterScreen : Screens()
    data class AllTickets(val title: String, val bottomTitle: String) : Screens()
}
