package com.example.choiceCountry.ui

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import com.example.common.RecyclerViewItemDecoration
import com.example.common.launchAndRepeatWithViewLifecycle
import com.example.common.showDatePickers
import com.example.common.subscribe
import com.example.di.CoreComponentProvider
import com.example.di.DaggerTwoModuleComponent
import com.example.di.NetworkModule
import com.example.navigation.Router
import com.example.search.databinding.FragmentChoiceCountryBinding
import javax.inject.Inject
import javax.inject.Named

class ChoiceCountryFragment : Fragment() {
    private var _binding: FragmentChoiceCountryBinding? = null
    private val binding get() = _binding!!

    @Named("Child")
    @Inject
    lateinit var routerChild: Router

    // private val navArgs by navArgs<ChoiceCountryFragmentArgs>()

    override fun onAttach(context: Context) {
        DaggerTwoModuleComponent
            .factory()
            .create(
                coreComponent = (requireActivity().application as CoreComponentProvider).coreComponent,
                networkModule = NetworkModule(requireActivity().application),
            ).inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var factory: ChoiceCountryViewModel.Factory

    private val viewModel: ChoiceCountryViewModel by viewModels {
        LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                city = arguments?.getString("title"),
                cityBottom = arguments?.getString("bottomTitle"),
            )
        }
    }
    private val adapter = ChoiceCountryAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentChoiceCountryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        Log.d("erwer", arguments?.getString("title").toString())
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
            is ChoiceScreenView.UiLabel.ShowScreenAllTickets -> {
                val bundle = Bundle()
                bundle.putString("topTitle", viewModel.topTextTitle)
                bundle.putString("bottomTitle", viewModel.bottomTextTitle)
                routerChild.navigateTo(uiLabel.screen, bundle)
            }
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
        routerChild.init(this, requireActivity().supportFragmentManager)
    }

    override fun onStop() {
        super.onStop()
        routerChild.clear()
    }
}
