package com.example.effectivemobile.presentation.choiceCountry

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.databinding.ChoiseCountryFragmentBinding
import com.example.effectivemobile.presentation.common.* // ktlint-disable no-wildcard-imports
import com.example.effectivemobile.presentation.mainscreen.LambdaFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named
@AndroidEntryPoint
class ChoiceCountryFragment() : Fragment() {
    private var _binding: ChoiseCountryFragmentBinding? = null
    private val binding get() = _binding!!

    private val navArgs by navArgs<ChoiceCountryFragmentArgs>()

    @Inject
    lateinit var factory: ChoiceCountryViewModel.Factory
    private val viewModel: ChoiceCountryViewModel by viewModels {
        com.example.effectivemobile.presentation.choiceCountry.LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                city = navArgs.title,
                cityBottom = navArgs.bottomTitle,
            )
        }
    }
    private val adapter = ChoiceCountryAdapter()

    @Named("Host")
    @Inject
    lateinit var router: Router
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = ChoiseCountryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        initViewModel()
        initView()
    }

    private fun initViewModel() {
        with(viewModel) {
            subscribe(uiLabels, ::handleUiLabel)
            launchAndRepeatWithViewLifecycle { uiState.collect(::handleState) }
        }
    }
    private fun handleState(model: ChoiceScreenView.Model): Unit =
        model.run {
            adapter.items = model.shortList
            binding.bvDateReturning.text = model.bottomTextDateOtp
            binding.bvToDate.text = model.bottomTextDatePrb
            binding.tvTop.text = model.textCityTop
            binding.tvSearchBottom.text = model.textCityBottom
        }
    private fun handleUiLabel(uiLabel: ChoiceScreenView.UiLabel): Unit =
        when (uiLabel) {
            is ChoiceScreenView.UiLabel.ShowScreenAllTickets -> router.navigateTo(uiLabel.screen)
            is ChoiceScreenView.UiLabel.ShowDatePicker -> showFilterDatePicker(uiLabel.date)
        }

    private fun showFilterDatePicker(date: Long?) {
        showDatePickers(date, onDateSelectClick = { date ->
            viewModel.onEvent(ChoiceScreenView.Event.OnUsedSelectDate(date))
        })
    }

    private fun initView() {
        with(binding) {
            rcWithTitle.apply {
                adapter = this@ChoiceCountryFragment.adapter
                setHasFixedSize(true)
                addItemDecoration(RecyclerViewItemDecoration())
            }
            bvCheckAllTickets.setOnClickListener {
                viewModel.onEvent(ChoiceScreenView.Event.OnClickLookAllTickets)
            }
            ivChange.setOnClickListener { viewModel.onEvent(ChoiceScreenView.Event.OnClickChangeIv) }
            ivClear.setOnClickListener { TODO() }
            bvDateReturning.setOnClickListener { viewModel.onEvent(ChoiceScreenView.Event.OnCalendarClick) }
            bvToDate.setOnClickListener { viewModel.onEvent(ChoiceScreenView.Event.OnCalendarClick) }
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        router.init(this, requireActivity().supportFragmentManager)
    }
    override fun onStop() {
        super.onStop()
        router.clear()
    }
}
