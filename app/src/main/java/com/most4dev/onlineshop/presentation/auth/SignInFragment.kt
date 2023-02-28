package com.most4dev.onlineshop.presentation.auth

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentSignInBinding
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : Fragment() {

    private var _binding: FragmentSignInBinding? = null
    private val binding: FragmentSignInBinding
        get() = _binding ?: throw RuntimeException("FragmentSignInBinding is null")

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()

        setObserves()
    }

    private fun setObserves() {
        authViewModel.signIn.observe(viewLifecycleOwner){
            //TODO open page1
            Log.d("TAGING", "OPEN PAGE 1")
        }
        authViewModel.signInError.observe(viewLifecycleOwner){
            binding.root.showSnackbar(it)
        }
        authViewModel.validDataSignIn.observe(viewLifecycleOwner){
            if (it){
                authViewModel.signIn(
                    AccountEntity(
                        binding.etEmail.text.toString(),
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etPassword.text.toString(),
                        ByteArray(0),
                    0
                    )
                )
            }
            else{
                binding.root.showSnackbar(requireContext().getString(R.string.data_not_valid))
            }
        }
        authViewModel.firstNameError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputFirstName, it)
        }

        authViewModel.lastNameError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputLastName, it)
        }

        authViewModel.emailError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputEmail, it)
        }

        authViewModel.passwordError.observe(viewLifecycleOwner){
            setErrorTextInputLayout(binding.inputPassword, it)
        }

    }

    private fun setErrorTextInputLayout(inputLayout: TextInputLayout, errorMessage: String){
        if (errorMessage == AuthViewModel.EMPTY_STRING){
            inputLayout.error = null
        }
        else{
            inputLayout.error = errorMessage
        }
    }

    private fun validateFields(){
        authViewModel.validateSignIn(
            binding.etFirstName.text.toString(),
            binding.etLastName.text.toString(),
            binding.etEmail.text.toString(),
            binding.etPassword.text.toString()
        )
    }

    private fun setClickListeners() {
        binding.txtLogIn.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_loginFragment)
        }
        binding.buttonSignIn.setOnClickListener {
            validateFields()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}