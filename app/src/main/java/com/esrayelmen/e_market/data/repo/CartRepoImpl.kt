package com.esrayelmen.e_market.data.repo

import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.domain.repo.CartRepo
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val dao: ProductDao
): CartRepo {

    override suspend fun addToCart(cartEntity: CartEntity)  {
       dao.addToCart(cartEntity)
    }

    override suspend fun getCartItems(): List<CartEntity> {
        return dao.getCartItems()
    }



}