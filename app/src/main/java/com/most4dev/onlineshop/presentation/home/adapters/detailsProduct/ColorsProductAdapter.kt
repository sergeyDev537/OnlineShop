package com.most4dev.onlineshop.presentation.home.adapters.detailsProduct


import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.most4dev.onlineshop.databinding.ItemColorBinding
import com.most4dev.onlineshop.domain.entities.SaleProductEntity

class ColorsProductAdapter :
    ListAdapter<String, ColorsProductViewHolder>(ColorsProductDiffCallback()) {

    var clickItemColorProduct: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorsProductViewHolder {
        val binding = ItemColorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ColorsProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorsProductViewHolder, position: Int) {
        val itemColor = getItem(position)
        val binding = holder.binding

        binding.root.setOnClickListener {
            clickItemColorProduct?.invoke(itemColor)
        }

        binding.constraintColor.setBackgroundColor(Color.parseColor(itemColor))

    }
}