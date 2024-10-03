package com.esrayelmen.e_market.domain.repo

import com.esrayelmen.e_market.data.model.CartEntity

interface CartRepo {

    suspend fun addToCart(cartEntity: CartEntity)

    suspend fun getCartItems() : List<CartEntity>

}