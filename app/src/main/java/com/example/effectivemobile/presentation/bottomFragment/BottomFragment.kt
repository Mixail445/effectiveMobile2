package com.example.effectivemobile.presentation.bottomFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.databinding.FragmentBottomNavBinding
import com.example.effectivemobile.presentation.common.Router
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named

@AndroidEntryPoint
class BottomFragment : Fragment() {
    private var _binding: FragmentBottomNavBinding? = null

    @Inject
    @Named("Host")
    lateinit var router: Router
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentBottomNavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        router.init(this, childFragmentManager, binding.bottomNavigationView)
    }

    override fun onStop() {
        super.onStop()
        router.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
