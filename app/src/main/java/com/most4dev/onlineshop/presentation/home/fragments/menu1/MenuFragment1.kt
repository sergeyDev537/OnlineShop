package com.most4dev.onlineshop.presentation.home.fragments.menu1

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.most4dev.onlineshop.databinding.FragmentMenu1Binding


class MenuFragment1 : Fragment() {

    private var _binding: FragmentMenu1Binding? = null
    private val binding: FragmentMenu1Binding
        get() = _binding ?: throw RuntimeException("FragmentMenu1Binding is null")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentMenu1Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}