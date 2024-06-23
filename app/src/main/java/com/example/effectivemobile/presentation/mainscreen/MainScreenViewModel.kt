package com.example.effectivemobile.presentation.mainscreen

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.domain.mainScreen.model.Offers
import com.example.effectivemobile.presentation.common.SaveStringLocalSource
import com.example.effectivemobile.presentation.common.Screens
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
@AssistedInject constructor(
    private val saveStringLocalSource: SaveStringLocalSource,
    private val repository: com.example.domain.mainScreen.MainScreenRepository,
    @Assisted private val state: SavedStateHandle,
    private val errorHandler: com.example.utils.ErrorHandel,
    private val mainScreenMapper: MainScreenMapper,
    @Assisted("cityTitle") private val city: String?,
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

        repository.getOffersRemote().onSuccess { response ->
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

    @AssistedFactory
    interface Factory {
        fun build(
            @Assisted state: SavedStateHandle,
            @Assisted("cityTitle") city: String?,
        ): MainScreenViewModel
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
