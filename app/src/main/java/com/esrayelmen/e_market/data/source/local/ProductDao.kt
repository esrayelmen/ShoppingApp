package com.esrayelmen.e_market.data.source.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.model.ProductResponse

@Dao
interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg products: ProductResponse) : List<Long>

    @Query("SELECT * FROM productresponse WHERE id = :id")
    suspend fun getProduct(id: Int) : ProductResponse

    @Query("SELECT * FROM productresponse WHERE name LIKE :query")
    suspend fun searchProducts(query: String) : List<ProductResponse>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartEntity: CartEntity)

    @Query ("SELECT * FROM CartEntity")
    suspend fun getCartItems() : List<CartEntity>





}