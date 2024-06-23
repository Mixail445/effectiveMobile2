package com.example.effectivemobile.presentation.mainscreen.dialog

import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import androidx.savedstate.SavedStateRegistryOwner
import com.example.effectivemobile.presentation.common.Screens
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

private const val STATE_KEY_DIALOG_SEARCH = "dialog_search"

class DialogSearchViewModel @AssistedInject constructor(
    @Assisted state: SavedStateHandle,
    @Assisted("title") private val title: String?,
) :
    ViewModel() {
    private val _uiLabels = MutableLiveData<DialogView.UiLabel>()
    val uiLabels: LiveData<DialogView.UiLabel> get() = _uiLabels

    private val _uiState =
        MutableStateFlow(
            state.get<DialogView.Model>(STATE_KEY_DIALOG_SEARCH) ?: DialogView.Model(title.orEmpty()),
        )
    val uiState: StateFlow<DialogView.Model> = _uiState.asStateFlow()

    fun onEvent(event: DialogView.Event): Unit =
        when (event) {
            DialogView.Event.OnClickIcAnywhere -> openEmptyScreen()
            DialogView.Event.OnClickIcFire -> openEmptyScreen()
            DialogView.Event.OnClickIcHard -> openEmptyScreen()
            DialogView.Event.OnClickIcWeekend -> openEmptyScreen()
            DialogView.Event.OnClickGroupOne -> openChoiceFragmentWithArg("Стамбул")
            DialogView.Event.OnClickGroupThree -> openChoiceFragmentWithArg("Пхукет")
            DialogView.Event.OnClickGroupTwo -> openChoiceFragmentWithArg("Сочи")
        }
    private fun openChoiceFragmentWithArg(city: String) {
        _uiLabels.value = DialogView.UiLabel.ShowSearchScreen(Screens.ChoiceCountry(title.orEmpty(), city))
    }
    private fun openEmptyScreen(){
        _uiLabels.value = DialogView.UiLabel.ShowEmptyScreen(Screens.EmptyScreen)
    }


    @AssistedFactory
    interface Factory {
        fun build(
            @Assisted state: SavedStateHandle,
            @Assisted("title")title: String?,
        ): DialogSearchViewModel
    }
    class LambdaFactory<T : ViewModel>(
        savedStateRegistryOwner: SavedStateRegistryOwner,
        private val create: (handle: SavedStateHandle) -> T,
    ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle,
        ): T {
            return create(handle) as T
        }
    }
}
