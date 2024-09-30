package com.esrayelmen.e_market.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.domain.repo.CartRepo
import com.esrayelmen.e_market.domain.repo.HomeRepo
import com.esrayelmen.e_market.util.BaseResult
import com.esrayelmen.e_market.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepo: HomeRepo,
    private val cartRepo: CartRepo
) : ViewModel() {

    init {
     //favoriler set edileceği zaman metod burada çağrılacak
    }

    private var _products = MutableStateFlow<List<ProductResponse>>(emptyList())
    val products: StateFlow<List<ProductResponse>> = _products

    private var _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    fun getProducts() {
        viewModelScope.launch {
            homeRepo.getProducts().collect { result ->
                val response = result.data
                when(result) {
                     is BaseResult.Success -> {
                         _isLoading.value = false
                        if (response != null) {
                            _products.value = response
                        }
                    }

                    is BaseResult.Loading -> {
                        _isLoading.value = true
                    }

                    is BaseResult.Error -> {
                        _isLoading.value = false
                    }
                }
            }
        }



        /*products.value = Resource.Loading()
        viewModelScope.launch {
            val response = homeRepo.getProducts()
            products.value = response
        }

         */
    }

    fun addToCart(product: ProductResponse) {
        viewModelScope.launch {
            product.id?.let { id ->
                cartRepo.addToCart(id,true)
            }
        }
    }


}