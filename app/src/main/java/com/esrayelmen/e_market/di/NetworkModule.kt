package com.esrayelmen.e_market.di

import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.data.repo.HomeRepoImpl
import com.esrayelmen.e_market.data.source.remote.ProductAPI
import com.esrayelmen.e_market.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun provideProductAPI(): ProductAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ProductAPI::class.java)
    }

}