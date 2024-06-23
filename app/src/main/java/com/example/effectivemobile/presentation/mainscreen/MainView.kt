package com.example.effectivemobile.presentation.mainscreen

import com.example.effectivemobile.presentation.common.Screens

class MainView {

    data class Model(
        val isLoading: Boolean = false,
        val offersItems: List<OffersUi?> = emptyList(),
        val textEdit: String,
    )
    sealed interface Event {
        data object OnClickSearch : Event
        data class OnSaveText(val text: String) : Event
    }
    sealed interface UiLabel {
        data class ShowSearchDialog(val screen: Screens) : UiLabel
        data class ShowError(val title: String?, val message: String) : UiLabel
    }
}
