package com.esrayelmen.e_market.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.esrayelmen.e_market.data.model.CartEntity
import com.esrayelmen.e_market.data.model.ProductResponse

@Database(entities = [ProductResponse::class, CartEntity::class], version = 2)
abstract class ProductDatabase : RoomDatabase() {

    abstract fun productDao() : ProductDao

}