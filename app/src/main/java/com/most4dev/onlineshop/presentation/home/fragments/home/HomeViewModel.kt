package com.most4dev.onlineshop.presentation.home.fragments.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.R
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
    application: Application,
    private val getCategoryProductsUseCase: GetCategoryProductsUseCase,
    private val getLatestProductsUseCase: GetLatestProductsUseCase,
    private val getFlashSaleProductsUseCase: GetFlashSaleProductsUseCase,
    private val getBrandsUseCase: GetBrandsUseCase,
) : AndroidViewModel(application) {

    private val context = application

    private var _listCategory = MutableLiveData<List<ItemCategory>>()
    val listCategory: LiveData<List<ItemCategory>> = _listCategory

    private var _listCategoryError = MutableLiveData<String>()
    val listCategoryError: LiveData<String> = _listCategoryError

    private var _listLatest = MutableLiveData<List<ProductEntity>>()
    val listLatest: LiveData<List<ProductEntity>> = _listLatest

    private var _listLatestError = MutableLiveData<String>()
    val listLatestError: LiveData<String> = _listLatestError

    private var _listSale = MutableLiveData<List<SaleProductEntity>>()
    val listSale: LiveData<List<SaleProductEntity>> = _listSale

    private var _listSaleError = MutableLiveData<String>()
    val listSaleError: LiveData<String> = _listSaleError

    private var _listBrands = MutableLiveData<List<BrandEntity>>()
    val listBrands: LiveData<List<BrandEntity>> = _listBrands

    private var _listBrandsError = MutableLiveData<String>()
    val listBrandsError: LiveData<String> = _listBrandsError

    init {
        getCategories()
        getLatestProducts()
        getFlashSaleProducts()
        getBrands()
    }

    private fun getCategories() {
        try {
            _listCategory.value = getCategoryProductsUseCase()
        } catch (e: Exception) {
            _listCategoryError.value = String.format(
                context.getString(R.string.error_list_category),
                e.message
            )
        }

    }

    private fun getLatestProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _listLatest.postValue(getLatestProductsUseCase() ?: listOf())
            } catch (e: Exception) {
                _listLatestError.postValue(
                    String.format(
                        context.getString(R.string.error_latest_products),
                        e.message
                    )
                )
            }

        }
    }

    private fun getFlashSaleProducts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _listSale.postValue(getFlashSaleProductsUseCase() ?: listOf())
            } catch (e: Exception) {
                _listSaleError.postValue(
                    String.format(
                        context.getString(R.string.error_sale_products),
                        e.message
                    )
                )
            }

        }
    }

    private fun getBrands() {
        try {
            _listBrands.value = getBrandsUseCase()
        } catch (e: Exception) {
            _listBrandsError.value = String.format(
                context.getString(R.string.error_brands),
                e.message
            )
        }

    }

}