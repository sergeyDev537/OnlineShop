package com.most4dev.onlineshop.presentation.home.fragments.profile

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentProfileBinding
import com.most4dev.onlineshop.domain.entities.AccountEntity
import com.most4dev.onlineshop.presentation.auth.AuthActivity
import com.most4dev.onlineshop.presentation.base.BaseFragment
import com.most4dev.onlineshop.utils.showSnackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val profileViewModel: ProfileViewModel by viewModel()

    private var account: AccountEntity? = null

    private lateinit var onUpdateProfileImage: UpdateProfileImageListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is UpdateProfileImageListener) {
            onUpdateProfileImage = context
        } else {
            throw RuntimeException("Activity must implement UpdateProfileImageListener")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setObserves()
        setClickListeners()
    }

    private fun setObserves() {
        profileViewModel.account.observe(viewLifecycleOwner) { accountEntity ->
            account = accountEntity
            accountEntity?.let {
                setData(it)
            }

        }
        profileViewModel.uploadPhotoError.observe(viewLifecycleOwner) {
            binding.root.showSnackbar(it)
        }
        profileViewModel.logoutError.observe(viewLifecycleOwner) {
            binding.root.showSnackbar(it)
        }
    }

    private fun setData(account: AccountEntity) {
        account.photoProfile?.let {
            Glide.with(requireContext())
                .load(account.photoProfile)
                .centerCrop()
                .placeholder(R.drawable.portrait_placeholder)
                .into(binding.imageProfile)
        } ?: Glide.with(requireContext())
            .load(R.drawable.portrait_placeholder)
            .centerCrop()
            .into(binding.imageProfile)

        binding.tvNameProfile.text = account.firstName.plus(account.lastName)
        binding.tvBalance.text = String.format(
            getString(R.string.label_price_latest),
            account.balance.toString()
        )
        onUpdateProfileImage.updateProfileImage(account.photoProfile)
    }

    private fun setClickListeners() {
        binding.tvChangePhoto.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_GRANTED
            ) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                resultLaunchChooseGallery.launch(intent)
            } else {
                resultPermissionLauncher.launch(
                    arrayOf(
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )
                )
            }
        }

        binding.llLogout.setOnClickListener {
            profileViewModel.logout()
            startActivity(AuthActivity.newInstance(requireActivity()))
            requireActivity().finish()
        }
    }

    private val resultLaunchChooseGallery: ActivityResultLauncher<Intent> =
        registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.data?.let {
                    setUploadPhoto(it)
                }
            }
        }

    private fun setUploadPhoto(
        uri: Uri,
    ) {
        account?.let {
            profileViewModel.uploadPhoto(
                requireContext(),
                uri,
                it
            )
        }

    }

    private val resultPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        )
        { permissions ->
            var permissionBoolean = false

            permissions.entries.forEach {
                if (it.value) {
                    permissionBoolean = true
                } else {
                    permissionBoolean = false
                    binding.root.showSnackbar(
                        getString(R.string.permission_denied) + it.key
                    )
                    return@forEach
                }
            }

            if (permissionBoolean) {
                val intent =
                    Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                resultLaunchChooseGallery.launch(intent)
            }
        }
}