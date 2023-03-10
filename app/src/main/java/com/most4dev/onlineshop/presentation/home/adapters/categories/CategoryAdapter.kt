package com.most4dev.onlineshop.presentation.home.adapters.categories

import com.bumptech.glide.Glide
import com.most4dev.onlineshop.databinding.ItemCategoryBinding
import com.most4dev.onlineshop.domain.entities.ItemCategory
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class CategoryAdapter : BaseAdapter<ItemCategory, ItemCategoryBinding>(ItemCategoryBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemCategory = getItem(position)
        val binding = (holder.binding) as ItemCategoryBinding
        val context = binding.root.context
        Glide.with(context).load(itemCategory.img).into(binding.iconCategory)
        binding.tvNameCategory.text = itemCategory.name

    }
}