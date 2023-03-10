package com.most4dev.onlineshop.presentation.home.adapters.detailsProduct

import com.bumptech.glide.Glide
import com.most4dev.onlineshop.databinding.ItemImageProductBinding
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class ImagesProductAdapter : BaseAdapter<String, ItemImageProductBinding>(ItemImageProductBinding::inflate) {

    var clickItemImageProduct: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemImageProduct = getItem(position)
        val binding = (holder.binding) as ItemImageProductBinding
        val context = binding.root.context

        binding.root.setOnClickListener {
            clickItemImageProduct?.invoke(itemImageProduct)
        }

        Glide.with(context).load(itemImageProduct)
            .centerCrop()
            .into(
                binding.root
            )

    }
}