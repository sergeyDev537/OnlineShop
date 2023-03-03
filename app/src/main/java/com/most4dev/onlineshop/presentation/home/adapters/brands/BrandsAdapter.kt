package com.most4dev.onlineshop.presentation.home.adapters.brands

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemBrandsBinding
import com.most4dev.onlineshop.domain.entities.BrandEntity

class BrandsAdapter : ListAdapter<BrandEntity, BrandsViewHolder>(BrandsDiffCallback()) {

    var clickItemBrand: ((BrandEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandsViewHolder {
        val binding = ItemBrandsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BrandsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandsViewHolder, position: Int) {
        val itemBrand = getItem(position)
        val binding = holder.binding
        val context = binding.root.context

        binding.root.setOnClickListener {
            clickItemBrand?.invoke(itemBrand)
        }

        Glide.with(context).load(itemBrand.imgURL)
            .centerCrop()
            .placeholder(R.drawable.no_image_placeholder)
            .into(binding.imageBrand)

    }
}