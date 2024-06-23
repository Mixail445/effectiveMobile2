package com.example.effectivemobile.presentation.mainscreen

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.fragment.navArgs
import com.example.effectivemobile.databinding.FragmentMainBinding
import com.example.effectivemobile.presentation.bottomFragment.BottomFragmentArgs
import com.example.effectivemobile.presentation.common.RecyclerViewItemDecoration
import com.example.effectivemobile.presentation.common.Router
import com.example.effectivemobile.presentation.common.launchAndRepeatWithViewLifecycle
import com.example.effectivemobile.presentation.common.subscribe
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class MainFragment() : Fragment() {
    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var factory: MainScreenViewModel.Factory
    private val viewModel: MainScreenViewModel by viewModels {
        LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                city = "navArgs.title",
            )
        }
    }
    private val adapter = MainScreenAdapter()

    @Named("Host")
    @Inject
    lateinit var router: Router


    @Named("Child")
    @Inject
    lateinit var routerChild: Router
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
            is MainView.UiLabel.ShowError -> TODO()
            is MainView.UiLabel.ShowSearchDialog -> router.navigateTo(uiLabel.screen)
        }
    private fun handleState(model: MainView.Model): Unit =
        model.run {
            adapter.items = model.offersItems
            binding.etInputOne.setText(model.textEdit)
        }

    private fun initView() {
        with(binding) {
            rcMain.apply {
                adapter = this@MainFragment.adapter
                setHasFixedSize(true)
                addItemDecoration(RecyclerViewItemDecoration())
            }
            etIntupTwo.setOnClickListener {
                viewModel.onEvent(MainView.Event.OnClickSearch)
            }
            etInputOne.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                }

                override fun afterTextChanged(p0: Editable?) {
                    viewModel.onEvent(MainView.Event.OnSaveText(p0.toString()))
                }
            })
        }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
    override fun onStart() {
        super.onStart()
        router.init(this, requireActivity().supportFragmentManager)
        routerChild.init(this, requireActivity().supportFragmentManager)
    }
    override fun onStop() {
        super.onStop()
        router.clear()
        routerChild.clear()
    }
}
