package com.esrayelmen.e_market.data.repo

import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.domain.repo.CartRepo
import javax.inject.Inject

class CartRepoImpl @Inject constructor(
    private val dao: ProductDao
): CartRepo {

    override suspend fun addToCart(cartEntity: CartEntity)  {
        val existingProduct = cartEntity.id?.let {
            dao.getCartProduct(it)
        }

        if (existingProduct != null) {
            existingProduct.quantity +=  1
            dao.updateQuantity(existingProduct)
            println(existingProduct.quantity)

        } else {
            //cartEntity.quantity = 1
            dao.addToCart(cartEntity)
        }
        getCartItems()
    }

    override suspend fun getCartItems(): List<CartEntity> {
        return dao.getCartItems()
    }

    override suspend fun deleteCartItems(cartEntity: CartEntity) {
        return dao.deleteCartItems(cartEntity)
    }

    override suspend fun updateProductQuantity(cartEntity: CartEntity, isIncrease: Boolean) {
        if (isIncrease) {
            cartEntity.quantity += 1
        } else {
            if (cartEntity.quantity > 1) {
                cartEntity.quantity -= 1
            } else
                deleteCartItems(cartEntity)
        }
        dao.updateQuantity(cartEntity)

    }




}