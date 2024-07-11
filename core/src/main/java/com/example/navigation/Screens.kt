package com.example.navigation

sealed class Screens {
    data object MainScreen : Screens()

    data class DialogSearch(
        val title: String,
    ) : Screens()

    data class ChoiceCountry(
        val title: String,
        val bottomTitle: String,
    ) : Screens()

    data object FilterScreen : Screens()

    data class AllTickets(
        val title: String,
        val bottomTitle: String,
    ) : Screens()

    data object HotelsScreen : Screens()

    data object LocationScreen : Screens()

    data object ProfileScreen : Screens()

    data object SubscribeScreen : Screens()
}
