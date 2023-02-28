package com.most4dev.onlineshop.presentation.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.most4dev.onlineshop.databinding.ActivityAuthBinding

class AuthActivity : AppCompatActivity() {

    private val binding: ActivityAuthBinding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

    }
}