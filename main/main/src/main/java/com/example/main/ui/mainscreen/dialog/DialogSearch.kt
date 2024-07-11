package com.example.main.ui.mainscreen.dialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.viewModels
import androidx.lifecycle.SavedStateHandle
import com.example.common.launchAndRepeatWithViewLifecycle
import com.example.common.subscribe
import com.example.di.CoreComponentProvider
import com.example.di.NetworkModule
import com.example.main.databinding.FragmentSearchDialogBinding
import com.example.main.di.DaggerMainComponent
import com.example.main.di.MainModule
import com.example.navigation.Router
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import javax.inject.Inject
import javax.inject.Named

class DialogSearch : BottomSheetDialogFragment() {
    private var _binding: FragmentSearchDialogBinding? = null
    private val binding get() = _binding!!

    @Named("Child")
    @Inject
    lateinit var routerChild: Router

    // private val navArgs by navArgs<DialogSearchArgs>()

    @Inject
    lateinit var factory: DialogSearchViewModel.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        initDagger()
        super.onCreate(savedInstanceState)
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

    private val viewModel: DialogSearchViewModel by viewModels {
        DialogSearchViewModel.LambdaFactory(this) { handle: SavedStateHandle ->
            factory.build(
                handle,
                arguments?.getString("title") ?: "",
            )
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        initViewModel()
        initView()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = FragmentSearchDialogBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = BottomSheetDialog(requireContext(), theme)
        dialog.setOnShowListener {
            val bottomSheetDialog = it as BottomSheetDialog
            val parentLayout =
                bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            parentLayout?.let { it ->
                val behaviour = BottomSheetBehavior.from(it)
                setupFullHeight(it)
                behaviour.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
        return dialog
    }

    private fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }

    private fun initView() {
        with(binding) {
            val clickListeners =
                mapOf(
                    tripBtn to DialogView.Event.OnClickIcFire,
                    anywhereBtn to DialogView.Event.OnClickIcAnywhere,
                    clearBtn to DialogView.Event.OnClickIcWeekend,
                    bestsBtn to DialogView.Event.OnClickIcHard,
                    groupOne to DialogView.Event.OnClickGroupOne,
                    groupTwo to DialogView.Event.OnClickGroupTwo,
                    groupThree to DialogView.Event.OnClickGroupThree,
                )

            clickListeners.forEach { (view, event) ->
                view.setOnClickListener { viewModel.onEvent(event) }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        routerChild.init(this, requireActivity().supportFragmentManager)
    }

    override fun onStop() {
        super.onStop()
        routerChild.clear()
    }

    private fun initViewModel() {
        with(viewModel) {
            subscribe(uiLabels, ::handleUiLabel)
            launchAndRepeatWithViewLifecycle { uiState.collect(::handleState) }
        }
    }

    private fun handleState(model: DialogView.Model): Unit =
        model.run {
            binding.fromEditTxt.setText(model.textTitle)
        }

    private fun handleUiLabel(uiLabel: DialogView.UiLabel): Unit =
        when (uiLabel) {
            is DialogView.UiLabel.ShowEmptyScreen -> routerChild.navigateTo(uiLabel.screen)
            is DialogView.UiLabel.ShowSearchScreen -> {
                val bundle = Bundle()
                bundle.putString("title", uiLabel.sendTopText)
                bundle.putString("bottomTitle", uiLabel.sendBottomText)
                routerChild.navigateTo(uiLabel.screen, bundle)
            }
        }
}
