package com.most4dev.onlineshop.presentation

import android.database.MatrixCursor
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.most4dev.onlineshop.domain.usecases.SearchProductUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainViewModel(
    private val searchProductUseCase: SearchProductUseCase
): ViewModel() {

    private val _listWords = MutableLiveData<MatrixCursor>()
    val listWords: LiveData<MatrixCursor> = _listWords

    fun getWords(string: String){
        viewModelScope.launch(Dispatchers.IO) {
            delay(AWAIT_DELAY)
            _listWords.postValue(searchProductUseCase(string))
        }
    }

    companion object{

        const val AWAIT_DELAY = 1000L

    }


}