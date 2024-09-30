package com.esrayelmen.e_market.di

import com.esrayelmen.e_market.data.source.local.ProductDao
import com.esrayelmen.e_market.data.source.remote.ProductAPI
import com.esrayelmen.e_market.data.repo.CartRepoImpl
import com.esrayelmen.e_market.data.repo.DetailRepoImpl
import com.esrayelmen.e_market.data.repo.HomeRepoImpl
import com.esrayelmen.e_market.domain.repo.CartRepo
import com.esrayelmen.e_market.domain.repo.DetailRepo
import com.esrayelmen.e_market.domain.repo.HomeRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {

    @Singleton
    @Provides
    fun provideHomeRepo(api: ProductAPI, dao: ProductDao) : HomeRepo = HomeRepoImpl(api, dao)

    @Singleton
    @Provides
    fun provideDetailRepo(dao: ProductDao) : DetailRepo = DetailRepoImpl(dao)


    @Provides
    fun provideCartRepo(dao: ProductDao) : CartRepo = CartRepoImpl(dao)


}