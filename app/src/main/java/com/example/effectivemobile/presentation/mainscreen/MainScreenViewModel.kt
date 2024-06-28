package com.example.effectivemobile.presentation.mainscreen

import androidx.lifecycle.*
import com.example.domain.mainScreen.model.Offers
import com.example.effectivemobile.presentation.common.SaveStringLocalSource
import com.example.effectivemobile.presentation.common.Screens
import com.example.utils.onError
import com.example.utils.onSuccess
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val STATE_KEY_MAIN_SCREEN = "FRAGMENT_STATE_KEY"

@HiltViewModel
class MainScreenViewModel
    @Inject
    constructor(
        private val saveStringLocalSource: SaveStringLocalSource,
        private val repository: com.example.domain.mainScreen.MainScreenRepository,
        private val state: SavedStateHandle,
        private val errorHandler: com.example.utils.ErrorHandel,
        private val mainScreenMapper: MainScreenMapper,
    ) : ViewModel() {
        private val sharedPref = saveStringLocalSource.getId()?.text.orEmpty()
        private val _uiState =
            MutableStateFlow(
                state.get<MainView.Model>(STATE_KEY_MAIN_SCREEN) ?: MainView.Model(textEdit = sharedPref),
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
            _uiLabels.value = MainView.UiLabel.ShowSearchDialog(Screens.DialogSearch(sharedPref))
        }

        private suspend fun initData() {
            requestMain()
        }

        private fun dataFromSharedPref(text: String) {
            saveStringLocalSource.setId(SaveDataInSharedPref(text))
            _uiState.update {
                it.copy(
                    textEdit = sharedPref,
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
            _uiLabels.value = MainView.UiLabel.ShowError("Error", errorHandler.handleError(throwable))
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
