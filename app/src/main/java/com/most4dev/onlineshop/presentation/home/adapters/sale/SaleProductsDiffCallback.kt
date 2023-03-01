package com.most4dev.onlineshop.presentation.home.adapters.sale

import androidx.recyclerview.widget.DiffUtil
import com.most4dev.onlineshop.domain.entities.SaleProductEntity

class SaleProductsDiffCallback: DiffUtil.ItemCallback<SaleProductEntity>() {

    override fun areItemsTheSame(oldItem: SaleProductEntity, newItem: SaleProductEntity): Boolean {
        return oldItem.image_url == newItem.image_url
    }

    override fun areContentsTheSame(oldItem: SaleProductEntity, newItem: SaleProductEntity): Boolean {
        return oldItem == newItem
    }
}