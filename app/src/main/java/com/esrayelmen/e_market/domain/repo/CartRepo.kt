package com.esrayelmen.e_market.domain.repo

import androidx.lifecycle.LiveData
import com.esrayelmen.e_market.data.model.ProductResponse

interface CartRepo {

    suspend fun addToCart(id: Int, isInCart: Boolean)

    suspend fun getCartItems() : List<ProductResponse>



}