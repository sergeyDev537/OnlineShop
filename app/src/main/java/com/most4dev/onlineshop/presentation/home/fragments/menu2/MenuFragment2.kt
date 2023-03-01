package com.most4dev.onlineshop.presentation.home.fragments.menu2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.most4dev.onlineshop.databinding.FragmentMenu2Binding

class MenuFragment2 : Fragment() {

    private var _binding: FragmentMenu2Binding? = null
    private val binding: FragmentMenu2Binding
        get() = _binding ?: throw RuntimeException("FragmentMenu2Binding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenu2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}