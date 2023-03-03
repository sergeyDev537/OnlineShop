package com.most4dev.onlineshop.presentation.home.fragments.itemProduct

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.R
import com.most4dev.onlineshop.domain.entities.ItemProductEntity
import com.most4dev.onlineshop.domain.usecases.GetProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemProductViewModel(
    application: Application,
    private val getProductUseCase: GetProductUseCase,
) : AndroidViewModel(application) {

    private val context = application

    private var _itemProduct = MutableLiveData<ItemProductEntity>()
    val itemProduct: LiveData<ItemProductEntity> = _itemProduct

    private var _itemProductError = MutableLiveData<String>()
    val itemProductError: LiveData<String> = _itemProductError

    init {
        getItemProduct()
    }

    private fun getItemProduct() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                getProductUseCase()?.let {
                    _itemProduct.postValue(it)
                }
            } catch (e: Exception) {
                _itemProductError.postValue(
                    String.format(
                        context.getString(R.string.error_item_product),
                        e.message
                    )
                )
            }
        }
    }
}