package com.example.effectivemobile.presentation

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.effectivemobile.databinding.FragmentBottomNavBinding
import com.example.effectivemobile.di.DaggerAppComponent
import com.example.main.di.MainModule
import com.example.navigation.Router
import javax.inject.Inject
import javax.inject.Named

class BottomFragment : Fragment() {
    private var _binding: FragmentBottomNavBinding? = null

    @Inject
    @Named("Child")
    lateinit var router: Router

    override fun onAttach(context: Context) {
        val dag =
            DaggerAppComponent.factory().create(
                application = requireActivity().application,
                mainModule = MainModule(requireActivity()),
            )
        dag.inject(this)
        super.onAttach(context)
    }

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
