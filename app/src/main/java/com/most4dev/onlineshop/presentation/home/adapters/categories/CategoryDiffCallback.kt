package com.most4dev.onlineshop.presentation.home.adapters.categories

import androidx.recyclerview.widget.DiffUtil
import com.most4dev.onlineshop.domain.entities.ItemCategory

class CategoryDiffCallback: DiffUtil.ItemCallback<ItemCategory>() {
    override fun areItemsTheSame(oldItem: ItemCategory, newItem: ItemCategory): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ItemCategory, newItem: ItemCategory): Boolean {
        return oldItem == newItem
    }
}