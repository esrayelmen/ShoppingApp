package com.esrayelmen.e_market.presentation.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.domain.repo.DetailRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repo: DetailRepo
) : ViewModel() {

    private val product = MutableLiveData<ProductResponse>()
    val _product : LiveData<ProductResponse>
        get() = product

    fun getProducts(id: Int) {
        viewModelScope.launch{
            val response = repo.getProduct(id)
            product.value = response
        }
    }
}