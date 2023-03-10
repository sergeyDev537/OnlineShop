package com.most4dev.onlineshop.presentation.home.adapters.latest

import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemLatestBinding
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class LatestProductsAdapter : BaseAdapter<ProductEntity, ItemLatestBinding>(ItemLatestBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemLatestProduct = getItem(position)
        val binding = (holder.binding) as ItemLatestBinding
        val context = binding.root.context

        Glide.with(context)
            .load(itemLatestProduct.image_urls)
            .centerCrop()
            .into(binding.imageLatest)

        binding.tvCategoryItem.text = itemLatestProduct.category
        binding.tvItemName.text = itemLatestProduct.name
        binding.tvPrice.text = String.format(
            context.getString(R.string.label_price_latest),
            itemLatestProduct.price
        )
    }
}