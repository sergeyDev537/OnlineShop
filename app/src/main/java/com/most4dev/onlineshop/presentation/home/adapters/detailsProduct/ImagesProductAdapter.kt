package com.most4dev.onlineshop.presentation.home.adapters.detailsProduct


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.databinding.ItemImageProductBinding

class ImagesProductAdapter :
    ListAdapter<String, ImagesProductViewHolder>(ImagesProductDiffCallback()) {

    var clickItemImageProduct: ((String) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesProductViewHolder {
        val binding = ItemImageProductBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ImagesProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesProductViewHolder, position: Int) {
        val itemImageProduct = getItem(position)
        val binding = holder.binding
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