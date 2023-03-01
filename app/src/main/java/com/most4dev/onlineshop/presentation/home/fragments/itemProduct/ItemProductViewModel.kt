package com.most4dev.onlineshop.presentation.home.fragments.itemProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.domain.entities.ItemProductEntity
import com.most4dev.onlineshop.domain.usecases.GetProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemProductViewModel(
    private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _itemProduct = MutableLiveData<ItemProductEntity>()
    val itemProduct: LiveData<ItemProductEntity> = _itemProduct

    init {
        getItemProduct()
    }

    private fun getItemProduct(){
        viewModelScope.launch(Dispatchers.IO) {
            getProductUseCase()?.let {
                _itemProduct.postValue(it)
            }

        }
    }

}