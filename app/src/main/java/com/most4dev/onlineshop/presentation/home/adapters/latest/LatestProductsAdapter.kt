package com.most4dev.onlineshop.presentation.home.adapters.latest

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemLatestBinding
import com.most4dev.onlineshop.domain.entities.ProductEntity

class LatestProductsAdapter : ListAdapter<ProductEntity, LatestProductsViewHolder>(
    LatestProductsDiffCallback()
) {

    var clickItemLatestProduct: ((ProductEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LatestProductsViewHolder {
        val binding = ItemLatestBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return LatestProductsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: LatestProductsViewHolder, position: Int) {
        val itemLatestProduct = getItem(position)
        val binding = holder.binding
        val context = binding.root.context

        binding.root.setOnClickListener {
            clickItemLatestProduct?.invoke(itemLatestProduct)
        }

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