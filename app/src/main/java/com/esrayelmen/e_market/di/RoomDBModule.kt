package com.esrayelmen.e_market.di

import android.content.Context
import androidx.room.Room
import com.esrayelmen.e_market.data.source.local.ProductDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomDBModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context, ProductDatabase::class.java, "productdatabase")
        .fallbackToDestructiveMigration()
        .build()

    @Singleton
    @Provides
    fun provideDao(database: ProductDatabase) = database.productDao()

}