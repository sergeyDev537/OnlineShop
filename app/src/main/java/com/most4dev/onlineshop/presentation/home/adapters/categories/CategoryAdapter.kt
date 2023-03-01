package com.most4dev.onlineshop.presentation.home.adapters.categories


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.databinding.ItemCategoryBinding
import com.most4dev.onlineshop.domain.entities.ItemCategory

class CategoryAdapter: ListAdapter<ItemCategory, CategoryViewHolder>(CategoryDiffCallback()) {

    var clickItemCategory: ((ItemCategory) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val binding = ItemCategoryBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val itemCategory = getItem(position)
        val binding = holder.binding
        val context = binding.root.context

        binding.root.setOnClickListener {
            clickItemCategory?.invoke(itemCategory)
        }

        Glide.with(context).load(itemCategory.img).into(binding.iconCategory)
        binding.tvNameCategory.text = itemCategory.name

    }
}