package com.most4dev.onlineshop.presentation.auth

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.most4dev.onlineshop.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private val binding: ActivityAuthBinding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    companion object {

        fun newInstance(activity: Activity): Intent {
            return Intent(activity, AuthActivity::class.java)
        }

    }

}