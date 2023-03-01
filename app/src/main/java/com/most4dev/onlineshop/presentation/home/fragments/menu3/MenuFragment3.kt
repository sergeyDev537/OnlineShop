package com.most4dev.onlineshop.presentation.home.fragments.menu3

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.most4dev.onlineshop.databinding.FragmentMenu3Binding

class MenuFragment3 : Fragment() {

    private var _binding: FragmentMenu3Binding? = null
    private val binding: FragmentMenu3Binding
        get() = _binding ?: throw RuntimeException("FragmentMenu3Binding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenu3Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}