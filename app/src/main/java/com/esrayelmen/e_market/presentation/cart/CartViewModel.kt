package com.esrayelmen.e_market.presentation.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.domain.repo.CartRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(
    private val repo: CartRepo
) : ViewModel() {

    private val cartList = MutableLiveData<List<CartEntity>>()
    val _cartList : LiveData<List<CartEntity>>
        get() = cartList

    fun getCartItems() {
        viewModelScope.launch {
            cartList.value = repo.getCartItems()
        }
    }

    fun deleteCartItems(cartEntity: CartEntity) {
        viewModelScope.launch {
            repo.deleteCartItems(cartEntity)
            getCartItems()
        }
    }

    fun updateProductQuantity(cartEntity: CartEntity, isIncrease: Boolean) {
        viewModelScope.launch {
            repo.updateProductQuantity(cartEntity, isIncrease)
            getCartItems()

        }

    }

}