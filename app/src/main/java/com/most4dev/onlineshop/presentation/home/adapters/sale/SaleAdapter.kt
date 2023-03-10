package com.most4dev.onlineshop.presentation.home.adapters.sale


import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.ItemSaleBinding
import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class SaleAdapter : BaseAdapter<SaleProductEntity, ItemSaleBinding>(ItemSaleBinding::inflate) {

    var clickItemSaleProduct: ((SaleProductEntity) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemSaleProduct = getItem(position)
        val binding = (holder.binding) as ItemSaleBinding
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