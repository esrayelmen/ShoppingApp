package com.esrayelmen.e_market.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esrayelmen.e_market.data.model.MenuVisibilityState
import com.esrayelmen.e_market.data.model.ProductResponse
import com.esrayelmen.e_market.domain.repo.HomeRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repo: HomeRepo
) : ViewModel() {

    private val _menuVisibility = MutableStateFlow(MenuVisibilityState())
    val menuVisibility: StateFlow<MenuVisibilityState>
        get() = _menuVisibility

    fun menuVisibility(isSearchVisible: Boolean, isFilterVisible: Boolean) {
        _menuVisibility.value = MenuVisibilityState(isSearchVisible,isFilterVisible)
    }

}