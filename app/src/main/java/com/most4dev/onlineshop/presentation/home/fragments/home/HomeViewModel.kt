package com.most4dev.onlineshop.presentation.home.fragments.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.domain.entities.BrandEntity
import com.most4dev.onlineshop.domain.entities.ItemCategory
import com.most4dev.onlineshop.domain.entities.ProductEntity
import com.most4dev.onlineshop.domain.entities.SaleProductEntity
import com.most4dev.onlineshop.domain.usecases.GetBrandsUseCase
import com.most4dev.onlineshop.domain.usecases.GetCategoryProductsUseCase
import com.most4dev.onlineshop.domain.usecases.GetFlashSaleProductsUseCase
import com.most4dev.onlineshop.domain.usecases.GetLatestProductsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val getLatestProductsUseCase: GetLatestProductsUseCase,
    private val getFlashSaleProductsUseCase: GetFlashSaleProductsUseCase,
    private val getBrandsUseCase: GetBrandsUseCase
): ViewModel() {

    private val _listCategory = MutableLiveData<List<ItemCategory>>()
    val listCategory: LiveData<List<ItemCategory>> = _listCategory

    private val _listLatest = MutableLiveData<List<ProductEntity>>()
    val listLatest: LiveData<List<ProductEntity>> = _listLatest

    private val _listSale = MutableLiveData<List<SaleProductEntity>>()
    val listSale: LiveData<List<SaleProductEntity>> = _listSale

    private val _listBrands = MutableLiveData<List<BrandEntity>>()
    val listBrands: LiveData<List<BrandEntity>> = _listBrands

    init {
        getCategories()
        getLatestProducts()
        getFlashSaleProducts()
        getBrands()
    }

    private fun getCategories(){
        _listCategory.value = getCategoryProductsUseCase()
    }

    private fun getLatestProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _listLatest.postValue(getLatestProductsUseCase() ?: listOf())
        }
    }

    private fun getFlashSaleProducts(){
        viewModelScope.launch(Dispatchers.IO) {
            _listSale.postValue(getFlashSaleProductsUseCase() ?: listOf())
        }
    }

    private fun getBrands(){
        _listBrands.value = getBrandsUseCase()
    }

}