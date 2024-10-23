package com.esrayelmen.e_market.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.domain.repo.CartRepo
import com.esrayelmen.e_market.domain.repo.FavoriteRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
open class BaseViewModel @Inject constructor(
    private val cartRepo: CartRepo,
    private val favoriteRepo: FavoriteRepo
) : ViewModel() {

    fun addToCart(product: ProductResponse) {
        viewModelScope.launch {
            product.id?.let { id ->
                cartRepo.addToCart(
                    CartEntity(
                        product.createdAt,
                        product.name,
                        product.imageUrl,
                        product.price,
                        product.description,
                        product.model,
                        product.brand,
                        id
                    )
                )

            }
        }
    }

    private var _isFavorite = MutableStateFlow<Boolean>(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite

    fun isFavorite(id: Int) {
        viewModelScope.launch {
            _isFavorite.value = favoriteRepo.isFavorite(id)
        }
    }

    private var _favorites = MutableStateFlow<List<ProductResponse>>(emptyList())
    val favorites: StateFlow<List<ProductResponse>> = _favorites

    fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = favoriteRepo.getFavorites()
        }
    }

    fun setFavorites(product: ProductResponse) {
        viewModelScope.launch {
            favoriteRepo.setFavorites(product)
            getFavorites()
        }
    }
}