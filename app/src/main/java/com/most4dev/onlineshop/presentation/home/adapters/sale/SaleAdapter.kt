package com.most4dev.onlineshop.presentation.home.adapters.sale


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemSaleBinding
import com.most4dev.onlineshop.domain.entities.SaleProductEntity

class SaleAdapter: ListAdapter<SaleProductEntity, SaleProductViewHolder>(SaleProductsDiffCallback()) {

    var clickItemSaleProduct: ((SaleProductEntity) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SaleProductViewHolder {
        val binding = ItemSaleBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return SaleProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SaleProductViewHolder, position: Int) {
        val itemSaleProduct = getItem(position)
        val binding = holder.binding
        val context = binding.root.context

        binding.root.setOnClickListener {
            clickItemSaleProduct?.invoke(itemSaleProduct)
        }

        Glide.with(context).load(itemSaleProduct.image_url)
            .centerCrop()
            .into(
            binding.imageSale
        )

        binding.tvCategoryItem.text = itemSaleProduct.category
        binding.tvItemName.text = itemSaleProduct.name
        binding.tvPrice.text = String.format(
            context.getString(R.string.label_price_latest),
            itemSaleProduct.price
        )
        binding.tvDiscount.text = String.format(
            context.getString(R.string.label_discount),
            itemSaleProduct.discount
        )

    }
}