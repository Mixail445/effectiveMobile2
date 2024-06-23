package com.example.effectivemobile.presentation.emptyFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.databinding.EmptyScreenFragmentBinding
import com.example.effectivemobile.presentation.common.Router
import com.example.effectivemobile.presentation.common.Screens
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class EmptyFragment() : Fragment() {
    private var _binding: EmptyScreenFragmentBinding? = null
    private val binding get() = _binding!!
    @Named("Host")
    @Inject
    lateinit var router: Router
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = EmptyScreenFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?,
    ) {
        initView()
    }
    override fun onStart() {
        super.onStart()
        router.init(this, requireActivity().supportFragmentManager)
    }
    private fun initView() {
        binding.button2.setOnClickListener {
            router.navigateTo(Screens.MainScreen)
        }
    }
}
