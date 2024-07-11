package com.example.main.ui.mainscreen

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
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
import com.example.di.NetworkModule
import com.example.main.databinding.FragmentMainBinding
import com.example.main.di.DaggerMainComponent
import com.example.main.di.MainModule
import com.example.navigation.Router
import javax.inject.Inject
import javax.inject.Named

class MainFragment : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Named("Child")
    @Inject
    lateinit var routerChild: Router

    override fun onAttach(context: Context) {
        initDagger()
        super.onAttach(context)
    }

    private fun initDagger() {
        DaggerMainComponent
            .factory()
            .create(
                coreComponent = (requireActivity().application as CoreComponentProvider).coreComponent,
                networkModule = NetworkModule(requireActivity().application),
                mainModule = MainModule(requireActivity().application),
            ).inject(this)
    }

    @Inject
    lateinit var factory: MainScreenViewModel.Factory

    private val viewModel: MainScreenViewModel by viewModels {
        MainScreenViewModel.LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
            )
        }
    }
    private val adapter = MainScreenAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
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

    private fun handleUiLabel(uiLabel: MainView.UiLabel): Unit =
        when (uiLabel) {
            is MainView.UiLabel.ShowError -> Unit
            is MainView.UiLabel.ShowSearchDialog -> {
                val bundle = Bundle()
                bundle.putString("title", viewModel.uiState.value.textEdit)
                routerChild.navigateTo(uiLabel.screen, bundle)
            }
        }

    private fun handleState(model: MainView.Model): Unit =
        model.run {
            adapter.items = model.offersItems
            binding.fromEditTxt.setText(model.textEdit)
        }

    private fun initView() {
        with(binding) {
            rcMain.apply {
                adapter = this@MainFragment.adapter
                setHasFixedSize(true)
                addItemDecoration(RecyclerViewItemDecoration())
            }
            toEditTxt.setOnClickListener {
                viewModel.onEvent(MainView.Event.OnClickSearch)
            }
            fromEditTxt.addTextChangedListener(
                object : TextWatcher {
                    override fun beforeTextChanged(
                        p0: CharSequence?,
                        p1: Int,
                        p2: Int,
                        p3: Int,
                    ) {
                    }

                    override fun onTextChanged(
                        p0: CharSequence?,
                        p1: Int,
                        p2: Int,
                        p3: Int,
                    ) {
                    }

                    override fun afterTextChanged(p0: Editable?) {
                        viewModel.onEvent(
                            MainView.Event
                                .OnSaveText(p0.toString()),
                        )
                    }
                },
            )
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
