package com.example.effectivemobile.presentation.emptyFragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.appComponent
import com.example.effectivemobile.databinding.FragmentEmptyScreenBinding
import com.example.effectivemobile.presentation.common.Router
import com.example.effectivemobile.presentation.common.Screens
import javax.inject.Inject
import javax.inject.Named

class EmptyFragment : Fragment() {
    private var _binding: FragmentEmptyScreenBinding? = null
    private val binding get() = _binding!!

    @Named("Host")
    @Inject
    lateinit var router: Router

    override fun onAttach(context: Context) {
        context.appComponent.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEmptyScreenBinding.inflate(inflater, container, false)
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
