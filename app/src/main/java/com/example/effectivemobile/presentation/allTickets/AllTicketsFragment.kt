package com.example.effectivemobile.presentation.allTickets

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.appComponent
import com.example.effectivemobile.databinding.FragmentAllTicketsBinding
import com.example.effectivemobile.presentation.common.RecyclerViewItemDecoration
import com.example.effectivemobile.presentation.common.Router
import com.example.effectivemobile.presentation.common.launchAndRepeatWithViewLifecycle
import com.example.effectivemobile.presentation.common.subscribe
import javax.inject.Inject
import javax.inject.Named

class AllTicketsFragment : Fragment() {
    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    private val navArgs by navArgs<AllTicketsFragmentArgs>()

    @Inject
    lateinit var factory: AllTicketsViewModel.Factory
    private val viewModel: AllTicketsViewModel by viewModels {
        LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                city = navArgs.title,
                bottomTitle = navArgs.bottomTitle,
            )
        }
    }
    private val adapter = AllTicketsAdapter()

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    @Named("Host")
    @Inject
    lateinit var router: Router

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentAllTicketsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        initViewModel()
        initView()
    }

    private fun initView() {
        with(binding) {
            rcAllTickets.apply {
                adapter = this@AllTicketsFragment.adapter
                setHasFixedSize(true)
                addItemDecoration(RecyclerViewItemDecoration())
            }
            ivBack.setOnClickListener { viewModel.onEvent(AllTicketsView.Event.OnClickBack) }
        }
    }

    private fun initViewModel() {
        with(viewModel) {
            subscribe(uiLabels, ::handleUiLabel)
            launchAndRepeatWithViewLifecycle { uiState.collect(::handleState) }
        }
    }

    private fun handleUiLabel(uiLabel: AllTicketsView.UiLabel): Unit =
        when (uiLabel) {
            is AllTicketsView.UiLabel.ShowBackScreen -> router.back()
        }

    private fun handleState(model: AllTicketsView.Model): Unit =
        model.run {
            adapter.items = model.allTicketsItem
            binding.tvCity.text = model.title
            binding.tvInfo.text = model.bottomTitle
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
