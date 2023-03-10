package com.most4dev.onlineshop.presentation.home.adapters.detailsProduct

import android.graphics.Color
import com.most4dev.onlineshop.databinding.ItemColorBinding
import com.most4dev.onlineshop.presentation.base.BaseAdapter
import com.most4dev.onlineshop.presentation.base.BaseViewHolder

class ColorsProductAdapter : BaseAdapter<String, ItemColorBinding>(ItemColorBinding::inflate) {

    var clickItemColorProduct: ((String) -> Unit)? = null

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val itemColor = getItem(position)
        val binding = (holder.binding) as ItemColorBinding

        binding.root.setOnClickListener {
            clickItemColorProduct?.invoke(itemColor)
        }

        binding.constraintColor.setBackgroundColor(Color.parseColor(itemColor))

    }
}