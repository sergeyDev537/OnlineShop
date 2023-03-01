package com.most4dev.onlineshop.presentation.home.fragments.itemProduct

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentItemProductBinding
import com.most4dev.onlineshop.domain.entities.ItemProductEntity
import com.most4dev.onlineshop.presentation.home.adapters.detailsProduct.ColorsProductAdapter
import com.most4dev.onlineshop.presentation.home.adapters.detailsProduct.ImagesProductAdapter
import com.most4dev.onlineshop.utils.shareProduct
import org.koin.androidx.viewmodel.ext.android.viewModel

class ItemProduct : Fragment() {

    private var _binding: FragmentItemProductBinding? = null
    private val binding: FragmentItemProductBinding
        get() = _binding ?: throw RuntimeException("FragmentItemProductBinding is null")

    private val itemProductViewModel: ItemProductViewModel by viewModel()

    private val imagesAdapter by lazy {
        ImagesProductAdapter()
    }

    private val colorsProductAdapter by lazy {
        ColorsProductAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentItemProductBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
        setAdapters()
        setObserves()
    }

    private fun setClickListeners() {
        binding.buttonPlus.setOnClickListener {
            addCountProduct()
        }
        binding.buttonMinus.setOnClickListener {
            removeCountProduct()
        }
        binding.buttonAddCart.setOnClickListener {
            confirmOrder()
        }
        binding.buttonCountProduct.setOnClickListener {
            confirmOrder()
        }

        binding.itemProductShare.setOnClickListener {
            requireActivity().shareProduct(TEST_URL_PRODUCT)
        }

        binding.itemProductLike.setOnClickListener {
            binding.itemProductLike.setImageResource(R.drawable.ic_like_item_added_product)
        }
    }

    private fun confirmOrder() {
        findNavController().navigate(R.id.action_itemProduct_to_orderFragment)
    }

    private fun removeCountProduct() {
        var currentInt = binding.tvCountProduct.text.toString().toInt()
        if (currentInt > DEFAULT_QUANTITY) {
            binding.tvCountProduct.text = (--currentInt).toString()
        } else {
            binding.tvCountProduct.text = DEFAULT_QUANTITY.toString()
        }
        setTotalSum()
    }

    private fun addCountProduct() {
        var currentInt = try {
            binding.tvCountProduct.text.toString().toInt()
        } catch (e: Exception) {
            DEFAULT_QUANTITY
        }
        binding.tvCountProduct.text = (++currentInt).toString()
        setTotalSum()
    }

    private fun setTotalSum() {
        binding.buttonCountProduct.text = String.format(
            getString(R.string.label_sum),
            calculateTotalSum().toString()
        )
    }

    private fun calculateTotalSum(): Int {
        val price = try {
            binding.tvPriceProduct.text.toString()
                .removePrefix(SYMBOL_CURRENCY)
                .replace(" ", "")
                .toInt()
        } catch (e: Exception) {
            DEFAULT_QUANTITY
        }
        val count = binding.tvCountProduct.text.toString().toInt()
        return price * count
    }

    private fun setAdapters() {
        binding.rvImagesProduct.adapter = imagesAdapter
        binding.rvColorsProduct.adapter = colorsProductAdapter

        imagesAdapter.clickItemImageProduct = {
            loadImageProduct(it)
        }

        colorsProductAdapter.clickItemColorProduct = {

        }

    }

    private fun setObserves() {
        itemProductViewModel.itemProduct.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    private fun setData(itemProduct: ItemProductEntity) {
        loadImageProduct(itemProduct.image_urls[0])
        imagesAdapter.submitList(itemProduct.image_urls)
        colorsProductAdapter.submitList(itemProduct.colors)
        binding.tvProductName.text = itemProduct.name
        binding.tvPriceProduct.text = String.format(
            getString(R.string.label_price_latest),
            itemProduct.price.toString()
        )
        binding.tvDescriptionProduct.text = itemProduct.description
        binding.tvRating.text = itemProduct.rating.toString()
        binding.tvCountReviews.text = String.format(
            getString(R.string.label_reviews),
            itemProduct.number_of_reviews.toString()
        )
    }

    private fun loadImageProduct(path: String) {
        Glide.with(requireContext())
            .load(path)
            .centerCrop()
            .into(binding.imageItemProduct)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val DEFAULT_QUANTITY = 0
        const val SYMBOL_CURRENCY = "$"
        const val TEST_URL_PRODUCT = "https://run.mocky.io/v3/f7f99d04-4971-45d5-92e0-70333383c239"

    }

}