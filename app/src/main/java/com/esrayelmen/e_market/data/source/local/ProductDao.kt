package com.esrayelmen.e_market.data.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
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


    @Query("SELECT * FROM productresponse WHERE favoriteStatus = 1")
    suspend fun getFavorites() : List<ProductResponse>

    @Query("SELECT favoriteStatus FROM productresponse WHERE id = :id")
    suspend fun isFavorite(id: Int) : Boolean

    @Query("UPDATE productresponse SET favoriteStatus = 1 WHERE id = :id")
    suspend fun addToFavorites(id: Int)

    @Query("UPDATE productresponse SET favoriteStatus = 0 WHERE id = :id")
    suspend fun deleteFavorite(id: Int)


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addToCart(cartEntity: CartEntity)

    @Query ("SELECT * FROM CartEntity")
    suspend fun getCartItems() : List<CartEntity>

    @Query("SELECT * FROM cartentity WHERE id = :id LIMIT 1")
    suspend fun getCartProduct(id: Int) : CartEntity?

    @Delete
    suspend fun deleteCartItems(cartEntity: CartEntity)

    @Update
    suspend fun updateQuantity(cartEntity: CartEntity)





}