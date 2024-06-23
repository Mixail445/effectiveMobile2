package com.example.effectivemobile.presentation.allTickets

import android.util.Log
import androidx.lifecycle.* // ktlint-disable no-wildcard-imports
import androidx.savedstate.SavedStateRegistryOwner
import com.example.domain.allTickets.AllTicketsRepository
import com.example.domain.allTickets.model.Tickets
import com.example.effectivemobile.presentation.common.Screens
import com.example.utils.DateUtils
import com.example.utils.format
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

class AllTicketsViewModel @AssistedInject constructor(
    private val repository: AllTicketsRepository,
    private val mapper: AllTicketsMapper,
    @Assisted private val state: SavedStateHandle,
    @Assisted("cityTitle") private val city: String?,
    @Assisted("bottomTitle")private val bottomTitle: String?,
) : ViewModel() {
    private val _uiState = MutableStateFlow(AllTicketsView.Model())
    val uiState: StateFlow<AllTicketsView.Model> = _uiState.asStateFlow()
    private val _uiLabels = MutableLiveData<AllTicketsView.UiLabel>()
    val uiLabels: LiveData<AllTicketsView.UiLabel> get() = _uiLabels
    private val bottomTitleF = DateUtils.formatDateString(bottomTitle.orEmpty())
    init {
        viewModelScope.launch {
            initData()
        }
    }

    private suspend fun initData() {
        requestMain()
    }
    fun onEvent(event: AllTicketsView.Event): Unit =
        when (event) {
            AllTicketsView.Event.OnClickBack -> showBackScreen()
        }

    private fun showBackScreen() {
        _uiLabels.value = AllTicketsView.UiLabel.ShowBackScreen(Screens.FilterScreen)
    }

    private suspend fun requestMain() {
        if (uiState.value.isLoading) return
        _uiState.update { it.copy(isLoading = true) }

        repository.getAllList().onSuccess { response ->
            _uiState.update {
                it.copy(
                    allTicketsItem = response.map { it.maToUi(mapperAllTickets = mapper) },
                    title = city.orEmpty(),
                    bottomTitle = bottomTitleF,
                )
            }
        }.onError { Log.d("error", it.message.toString()) }

        _uiState.update { it.copy(isLoading = false) }
    }

    @AssistedFactory
    interface Factory {
        fun build(
            @Assisted state: SavedStateHandle,
            @Assisted("cityTitle") city: String?,
            @Assisted("bottomTitle") bottomTitle: String?,
        ): AllTicketsViewModel
    }
}
fun Tickets.maToUi(mapperAllTickets: AllTicketsMapper) = AllTicketsUi(
    id = id,
    badge = badge,
    price = mapperAllTickets.formatNumber(price.value),
    providerName = mapperAllTickets.calculateTimeDifference(departure, arrival).toString(),
    company = company,
    departure = departure.format(DateUtils.UI_ITEM_ALL_TICKETS_FORMAT).orEmpty(),
    arrival = arrival.format(DateUtils.UI_ITEM_ALL_TICKETS_FORMAT).orEmpty(),
    hasTransfer = hasTransfer,
    hasVisaTransfer = hasVisaTransfer,
    codeOne = luggage,
    codeTwo = handLuggage,
    isReturnable = isReturnable,
    isExchangable = isReturnable,
    itemId = id.toString(),
)
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
