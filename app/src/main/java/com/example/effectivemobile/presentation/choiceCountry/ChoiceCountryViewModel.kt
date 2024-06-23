package com.example.effectivemobile.presentation.choiceCountry

import androidx.lifecycle.*
import androidx.savedstate.SavedStateRegistryOwner
import com.example.domain.choiseCountry.model.TicketsOffer
import com.example.effectivemobile.presentation.common.Screens
import com.example.utils.onError
import com.example.utils.onSuccess
import com.example.utils.toEpochMillis
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class ChoiceCountryViewModel @AssistedInject constructor(
    @Assisted private val state1: SavedStateHandle,
    private val repository: com.example.domain.choiseCountry.ChoiceCountryRepository,
    private val mapper: ChoiceCountryMapper,
    @Assisted("cityTitle") private val city: String?,
    @Assisted("bottomTitle") private val cityBottom: String?,
) : ViewModel() {
    private var searchDate: LocalDateTime? = null
    private val state = MutableStateFlow(ChoiceCountryAllData.State())
    val uiState = state.map { it.mapToUi() }
    private val _uiLabels = MutableLiveData<ChoiceScreenView.UiLabel>()
    val uiLabels: LiveData<ChoiceScreenView.UiLabel> get() = _uiLabels

    private val topTextTitle: String = city.orEmpty()
    private val bottomTextTitle: String = cityBottom.orEmpty()
    private var dateTo: LocalDateTime? = null
    private val dateOut: LocalDateTime? = null
    private val currentDate = com.example.utils.DateUtils.getCurrentDate().orEmpty()

    init {
        viewModelScope.launch {
            initData()
        }
    }

    private suspend fun initData() {
        requestMain()
        state.update { it.copy() }
    }

    fun onEvent(event: ChoiceScreenView.Event): Unit =
        when (event) {
            is ChoiceScreenView.Event.OnCalendarClick -> handleOnCalendarClick()
            ChoiceScreenView.Event.OnClickLookAllTickets -> showAllTicketsFrag()
            ChoiceScreenView.Event.OnClickChangeIv -> changeTextTopBottom()
            is ChoiceScreenView.Event.OnUsedSelectDate -> showDatePicker(event.date)
        }

    private fun showAllTicketsFrag() {
        _uiLabels.value = ChoiceScreenView.UiLabel.ShowScreenAllTickets(Screens.AllTickets(title = "$topTextTitle-$bottomTextTitle", bottomTitle = currentDate))
    }

    private fun changeTextTopBottom() {
        state.update {
            it.copy(
                textCityTop = bottomTextTitle,
                textCityBottom = topTextTitle,
                bottomTextDatePrb = currentDate,
            )
        }
    }

    private fun handleOnCalendarClick() {
        _uiLabels.value = ChoiceScreenView.UiLabel.ShowDatePicker(searchDate?.toEpochMillis())
    }

    private fun showDatePicker(date: Long?) {
        dateTo = date?.let { com.example.utils.DateUtils.parseLocalDateTime(it) }
        state.update {
            it.copy(
                bottomTextDateOtp = com.example.utils.DateUtils.getCalendarUiDate(dateTo),
            )
        }
    }
    private suspend fun requestMain() {
        state.update { it.copy(isLoading = true) }

        repository.shortListTickets().onSuccess { response ->
            state.update {
                it.copy(
                    shortTickets = response.map { it.mapToUi(mapper) },
                    bottomTextForRcUi = listOf(BottomTextForRcUi("4", "Показать все")),
                    topTitleForRc = listOf(TitleTextForRcUi("Прямые рейсы", "0")),
                    textCityBottom = bottomTextTitle,
                    textCityTop = topTextTitle,
                    bottomTextDateOtp = "24 фев, сб",
                    bottomTextDatePrb = "+ обратно",
                )
            }
        }.onError {
            state.update { it.copy(isLoading = false) }
        }
    }

    @AssistedFactory
    interface Factory {
        fun build(
            @Assisted state1: SavedStateHandle,
            @Assisted("cityTitle") city: String?,
            @Assisted("bottomTitle") cityBottom: String?,
        ): ChoiceCountryViewModel
    }
}
fun TicketsOffer.mapToUi(mapper: ChoiceCountryMapper) = ChoiceCountryUi(
    id = id,
    itemId = id.toString(),
    title = title,
    timeRange = timeRange.joinToString(" "),
    price = mapper.formatNumber(price),
    viewColor = mapper.getColorByIndex(timeRange),
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
