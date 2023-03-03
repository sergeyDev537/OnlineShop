package com.most4dev.onlineshop.presentation.home.adapters.detailsProduct

import androidx.recyclerview.widget.DiffUtil

class ColorsProductDiffCallback : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }
}