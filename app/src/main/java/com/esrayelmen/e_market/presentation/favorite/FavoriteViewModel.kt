package com.esrayelmen.e_market.presentation.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.domain.repo.CartRepo
import com.esrayelmen.e_market.domain.repo.FavoriteRepo
import com.esrayelmen.e_market.domain.repo.HomeRepo
import com.esrayelmen.e_market.presentation.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val repo: FavoriteRepo,
    cartRepo: CartRepo
) : BaseViewModel(cartRepo, repo) {

    /*private var _favorites = MutableStateFlow<List<ProductResponse>>(emptyList())
    val favorites: StateFlow<List<ProductResponse>> = _favorites

    fun getFavorites() {
        viewModelScope.launch {
            _favorites.value = repo.getFavorites()
        }
    }

     */






}