package com.most4dev.onlineshop.presentation.home.adapters.latest

import androidx.recyclerview.widget.DiffUtil
import com.most4dev.onlineshop.domain.entities.ProductEntity

class LatestProductsDiffCallback: DiffUtil.ItemCallback<ProductEntity>() {

    override fun areItemsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: ProductEntity, newItem: ProductEntity): Boolean {
        return oldItem == newItem
    }
}