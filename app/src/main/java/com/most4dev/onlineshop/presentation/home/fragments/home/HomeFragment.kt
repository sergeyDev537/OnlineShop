package com.most4dev.onlineshop.presentation.home.fragments.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.databinding.FragmentHomeBinding
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import com.most4dev.onlineshop.presentation.home.adapters.brands.BrandsAdapter
import com.most4dev.onlineshop.presentation.home.adapters.categories.CategoryAdapter
import com.most4dev.onlineshop.presentation.home.adapters.latest.LatestProductsAdapter
import com.most4dev.onlineshop.presentation.home.adapters.sale.SaleAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private var listLatestProducts: List<ProductEntity> = arrayListOf()
    private var listSaleProducts: List<SaleProductEntity> = arrayListOf()

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding ?: throw RuntimeException("FragmentHomeBinding is null")

    private val homeViewModel: HomeViewModel by viewModel()

    private val categoryAdapter: CategoryAdapter by lazy {
        CategoryAdapter()
    }

    private val latestProductsAdapter: LatestProductsAdapter by lazy {
        LatestProductsAdapter()
    }

    private val saleProductsAdapter: SaleAdapter by lazy {
        SaleAdapter()
    }

    private val brandsAdapter: BrandsAdapter by lazy {
        BrandsAdapter()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapters()
        setObserves()
    }

    private fun setAdapters() {
        binding.rvCategories.adapter = categoryAdapter
        binding.rvLatest.adapter = latestProductsAdapter
        binding.rvFlashSale.adapter = saleProductsAdapter
        binding.rvBrands.adapter = brandsAdapter

        categoryAdapter.clickItemCategory = {

        }

        latestProductsAdapter.clickItemLatestProduct = {

        }

        saleProductsAdapter.clickItemSaleProduct = {
            findNavController().navigate(R.id.action_nav_bottom_home_to_itemProduct)
        }

        brandsAdapter.clickItemBrand = {

        }
    }

    private fun setObserves() {
        homeViewModel.listCategory.observe(viewLifecycleOwner){
            categoryAdapter.submitList(it)
        }
        homeViewModel.listLatest.observe(viewLifecycleOwner){
            checkListLatestProducts(it)
        }
        homeViewModel.listSale.observe(viewLifecycleOwner){
            checkListSaleProducts(it)
        }
        homeViewModel.listBrands.observe(viewLifecycleOwner){
            brandsAdapter.submitList(it)
        }
    }

    private fun checkListLatestProducts(productEntities: List<ProductEntity>) {
        if (productEntities.isNotEmpty()){
            listLatestProducts = productEntities
            if (listSaleProducts.isNotEmpty()){
                visibleLatestSaleProducts(true)
                latestProductsAdapter.submitList(productEntities)
                saleProductsAdapter.submitList(listSaleProducts)
            }
            else{
                visibleLatestSaleProducts(false)
            }
        }
        else{
            visibleLatestSaleProducts(false)
        }
    }

    private fun checkListSaleProducts(saleProducts: List<SaleProductEntity>) {
        if (saleProducts.isNotEmpty()){
            listSaleProducts = saleProducts
            if (listLatestProducts.isNotEmpty()){
                visibleLatestSaleProducts(true)
                latestProductsAdapter.submitList(listLatestProducts)
                saleProductsAdapter.submitList(saleProducts)
            }
            else{
                visibleLatestSaleProducts(false)
            }
        }
        else{
            visibleLatestSaleProducts(false)
        }
    }



    private fun visibleLatestSaleProducts(visible: Boolean){
        if (visible){
            binding.tvLatest.visibility = View.VISIBLE
            binding.rvLatest.visibility = View.VISIBLE
            binding.tvFlashSale.visibility = View.VISIBLE
            binding.rvFlashSale.visibility = View.VISIBLE
        }
        else{
            binding.tvLatest.visibility = View.GONE
            binding.rvLatest.visibility = View.GONE
            binding.tvFlashSale.visibility = View.GONE
            binding.rvFlashSale.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}