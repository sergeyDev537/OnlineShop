package com.most4dev.onlineshop.presentation.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.textfield.TextInputLayout
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentLoginBinding
import com.most4dev.onlineshop.presentation.MainActivity
import com.most4dev.onlineshop.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding: FragmentLoginBinding
        get() = _binding ?: throw RuntimeException("FragmentLoginBinding is null")

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setObserves()
    }

    private fun setObserves() {
        authViewModel.login.observe(viewLifecycleOwner){
            if (!it){
                binding.root.showSnackbar(requireContext().getString(R.string.name_email_not_valid))
                loadData(false)
            }
            else{
                startActivity(MainActivity.newInstance(requireActivity()))
                requireActivity().finish()
            }
        }
        authViewModel.validDataLogIn.observe(viewLifecycleOwner){
            if (it){
                authViewModel.login(
                    binding.etFirstName.text.toString(),
                    binding.etPassword.text.toString()
                )
            }
        }
        authViewModel.firstNameLogInError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputFirstName, it)
        }
        authViewModel.passwordLogInError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputPassword, it)
        }
        authViewModel.loginError.observe(viewLifecycleOwner){
            binding.root.showSnackbar(it)
            loadData(false)
        }
    }

    private fun setErrorTextInputLayout(inputLayout: TextInputLayout, errorMessage: String){
        if (errorMessage == AuthViewModel.EMPTY_STRING){
            inputLayout.error = null
        }
        else{
            inputLayout.error = errorMessage
            loadData(false)
        }
    }

    private fun setClickListeners() {
        binding.buttonLogin.setOnClickListener {
            loadData(true)
            authViewModel.validateLogIn(
                binding.etFirstName.text.toString(),
                binding.etPassword.text.toString()
            )
        }
    }

    private fun loadData(loading: Boolean){
        if (loading){
            binding.buttonLogin.isEnabled = false
            binding.progressLogin.visibility = View.VISIBLE
        }
        else{
            binding.buttonLogin.isEnabled = true
            binding.progressLogin.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}