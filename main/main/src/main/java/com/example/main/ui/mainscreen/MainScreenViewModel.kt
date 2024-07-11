@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.example.main.ui.mainscreen

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.main.domain.mainScreen.MainScreenRepository
import com.example.main.domain.mainScreen.model.Offers
import com.example.main.utils.SaveStringLocalSource
import com.example.navigation.Screens
import com.example.utils.ErrorHandel
import com.example.utils.onError
import com.example.utils.onSuccess
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val STATE_KEY_MAIN_SCREEN = "FRAGMENT_STATE_KEY"

class MainScreenViewModel
    @AssistedInject
    constructor(
        private val saveStringLocalSource: SaveStringLocalSource,
        private val repository: MainScreenRepository,
        @Assisted private val state: SavedStateHandle,
        private val errorHandler: ErrorHandel,
        private val mainScreenMapper: MainScreenMapper,
    ) : ViewModel() {
        private val _uiState =
            MutableStateFlow(
                state.get<MainView.Model>(STATE_KEY_MAIN_SCREEN) ?: MainView.Model(textEdit = saveStringLocalSource.getId()?.item ?: ""),
            )
        val uiState: StateFlow<MainView.Model> = _uiState.asStateFlow()
        private val _uiLabels = MutableLiveData<MainView.UiLabel>()
        val uiLabels: LiveData<MainView.UiLabel> get() = _uiLabels

        init {
            viewModelScope.launch {
                initData()
            }
        }

        fun onEvent(event: MainView.Event): Unit =
            when (event) {
                MainView.Event.OnClickSearch -> showDialog()
                is MainView.Event.OnSaveText -> dataFromSharedPref(event.text)
            }

        private fun showDialog() {
            _uiLabels.value =
                MainView.UiLabel.ShowSearchDialog(
                    Screens
                        .DialogSearch(saveStringLocalSource.getId()?.item.toString()),
                )
        }

        private suspend fun initData() {
            requestMain()
        }

        private fun dataFromSharedPref(text: String) {
            saveStringLocalSource.setId(DataForSharedPreferences(text))
            _uiState.update {
                it.copy(
                    textEdit = saveStringLocalSource.getId()?.item ?: "",
                )
            }
        }

        private suspend fun requestMain() {
            if (uiState.value.isLoading) return
            _uiState.update { it.copy(isLoading = true) }

            repository
                .getOffersRemote()
                .onSuccess { response ->
                    _uiState.update {
                        it.copy(
                            offersItems = response?.listData?.map { it.mapToUi(mainScreenMapper = mainScreenMapper) }.orEmpty(),
                        )
                    }
                }.onError { it.cause?.let { it1 -> processError(it1) } }

            _uiState.update { it.copy(isLoading = false) }
        }

        private fun processError(throwable: Throwable) {
            _uiLabels.value =
                MainView.UiLabel.ShowError("Error", errorHandler.handleError(throwable))
        }

        @AssistedFactory
        interface Factory {
            fun build(
                @Assisted state: SavedStateHandle,
            ): MainScreenViewModel
        }

        class LambdaFactory<T : ViewModel>(
            savedStateRegistryOwner: SavedStateRegistryOwner,
            private val create: (handle: SavedStateHandle) -> T,
        ) : AbstractSavedStateViewModelFactory(savedStateRegistryOwner, null) {
            override fun <T : ViewModel> create(
                key: String,
                modelClass: Class<T>,
                handle: SavedStateHandle,
            ): T = create(handle) as T
        }
    }

fun Offers.mapToUi(mainScreenMapper: MainScreenMapper) =
    mainScreenMapper.list[id.toInt() - 1]?.let {
        OffersUi(
            id = id,
            title = title,
            town = town,
            price = mainScreenMapper.formatNumber(price.value),
            itemId = id.toString(),
            photo = it,
        )
    }
