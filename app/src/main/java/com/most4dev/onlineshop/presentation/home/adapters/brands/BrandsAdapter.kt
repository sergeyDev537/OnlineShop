package com.most4dev.onlineshop.presentation.home.adapters.brands

import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemBrandsBinding
import com.most4dev.onlineshop.domain.entities.BrandEntity
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class BrandsAdapter : BaseAdapter<BrandEntity, ItemBrandsBinding>(ItemBrandsBinding::inflate) {

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemBrand = getItem(position)
        val binding = (holder.binding) as ItemBrandsBinding
        val context = binding.root.context

        Glide.with(context).load(itemBrand.imgURL)
            .centerCrop()
            .placeholder(R.drawable.no_image_placeholder)
            .into(binding.imageBrand)
    }
}