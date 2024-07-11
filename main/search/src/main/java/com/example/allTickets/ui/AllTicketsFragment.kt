package com.example.allTickets.ui

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
import com.example.common.subscribe
import com.example.di.CoreComponentProvider
import com.example.di.DaggerTwoModuleComponent
import com.example.di.NetworkModule
import com.example.navigation.Router
import com.example.search.databinding.FragmentAllTicketsBinding
import javax.inject.Inject
import javax.inject.Named

class AllTicketsFragment : Fragment() {
    private var _binding: FragmentAllTicketsBinding? = null
    private val binding get() = _binding!!
    // private val navArgs by navArgs<AllTicketsFragmentArgs>()

    @Inject
    lateinit var factory: AllTicketsViewModel.Factory

    private val viewModel: AllTicketsViewModel by viewModels {
        LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                city = arguments?.getString("topTitle"), // /navArgs.title,
                bottomTitle = arguments?.getString("bottomTitle"), // navArgs.bottomTitle,
            )
        }
    }
    private val adapter = AllTicketsAdapter()

    override fun onAttach(context: Context) {
        initDagger()
        super.onAttach(context)
    }

    private fun initDagger() {
        DaggerTwoModuleComponent
            .factory()
            .create(
                coreComponent = (requireActivity().application as CoreComponentProvider).coreComponent,
                networkModule = NetworkModule(requireActivity().application),
            ).inject(this)
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
        Log.d("topAll", arguments?.getString("topTitle") ?: "")
        Log.d("bottomAll", arguments?.getString("bottomTitle") ?: "")
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
