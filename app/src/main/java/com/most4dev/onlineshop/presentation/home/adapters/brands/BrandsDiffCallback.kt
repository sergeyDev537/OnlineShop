package com.most4dev.onlineshop.presentation.home.adapters.brands

import androidx.recyclerview.widget.DiffUtil
import com.most4dev.onlineshop.domain.entities.BrandEntity

class BrandsDiffCallback : DiffUtil.ItemCallback<BrandEntity>() {

    override fun areItemsTheSame(oldItem: BrandEntity, newItem: BrandEntity): Boolean {
        return oldItem.imgURL == newItem.imgURL
    }

    override fun areContentsTheSame(oldItem: BrandEntity, newItem: BrandEntity): Boolean {
        return oldItem == newItem
    }
}