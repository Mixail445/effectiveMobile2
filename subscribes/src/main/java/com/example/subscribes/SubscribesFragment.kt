package com.example.subscribes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.subscribes.databinding.FragmentSubscribesBinding

class SubscribesFragment : Fragment() {
    private var _binding: FragmentSubscribesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSubscribesBinding.inflate(inflater, container, false)
        return binding.root
    }
}
