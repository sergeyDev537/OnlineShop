package com.most4dev.onlineshop.presentation.auth

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.textfield.TextInputLayout
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentSignInBinding
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.presentation.MainActivity
import com.most4dev.onlineshop.presentation.base.BaseFragment
import com.most4dev.onlineshop.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignInFragment : BaseFragment<FragmentSignInBinding>(FragmentSignInBinding::inflate) {

    private val authViewModel by viewModel<AuthViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setObserves()
    }

    private fun setObserves() {
        authViewModel.signIn.observe(viewLifecycleOwner) {
            startActivity(MainActivity.newInstance(requireActivity()))
            requireActivity().finish()
        }
        authViewModel.signInError.observe(viewLifecycleOwner) {
            binding.root.showSnackbar(it)
        }
        authViewModel.validDataSignIn.observe(viewLifecycleOwner) {
            if (it) {
                authViewModel.signIn(
                    AccountEntity(
                        binding.etEmail.text.toString(),
                        binding.etFirstName.text.toString(),
                        binding.etLastName.text.toString(),
                        binding.etPassword.text.toString(),
                        EMPTY_BITMAP,
                        DEFAULT_BALANCE
                    )
                )
            } else {
                binding.root.showSnackbar(requireContext().getString(R.string.data_not_valid))
            }
        }
        authViewModel.firstNameError.observe(viewLifecycleOwner) {
            setErrorTextInputLayout(binding.inputFirstName, it)
        }

        authViewModel.lastNameError.observe(viewLifecycleOwner) {
            setErrorTextInputLayout(binding.inputLastName, it)
        }

        authViewModel.emailError.observe(viewLifecycleOwner) {
            setErrorTextInputLayout(binding.inputEmail, it)
        }

        authViewModel.passwordError.observe(viewLifecycleOwner) {
            setErrorTextInputLayout(binding.inputPassword, it)
        }

    }

    private fun setErrorTextInputLayout(inputLayout: TextInputLayout, errorMessage: String) {
        if (errorMessage == AuthViewModel.EMPTY_STRING) {
            inputLayout.error = null
        } else {
            inputLayout.error = errorMessage
            loadData(false)
        }
    }

    private fun validateFields() {
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
            loadData(true)
            validateFields()
        }
    }

    private fun loadData(loading: Boolean) {
        if (loading) {
            binding.buttonSignIn.isEnabled = false
            binding.inputFirstName.isEnabled = false
            binding.inputLastName.isEnabled = false
            binding.inputEmail.isEnabled = false
            binding.inputPassword.isEnabled = false
            binding.llSignInGoogle.isEnabled = false
            binding.llSignInApple.isEnabled = false
            binding.progressSignIn.visibility = View.VISIBLE
        } else {
            binding.buttonSignIn.isEnabled = true
            binding.inputFirstName.isEnabled = true
            binding.inputLastName.isEnabled = true
            binding.inputEmail.isEnabled = true
            binding.inputPassword.isEnabled = true
            binding.llSignInGoogle.isEnabled = true
            binding.llSignInApple.isEnabled = true
            binding.progressSignIn.visibility = View.GONE
        }
    }

    companion object {

        val EMPTY_BITMAP = Bitmap.createBitmap(1, 1, Bitmap.Config.ARGB_8888)
        const val DEFAULT_BALANCE = 1593

    }

}