package com.example.newsapp.di

import com.example.newsapp.data.repository.ResponseCheckRepositoryImpl
import com.example.newsapp.domain.repository.ResponseCheckRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
    abstract class RepositoryModule {

        @Binds
        abstract fun bindNewsRepository(
            impl: ResponseCheckRepositoryImpl
        ): ResponseCheckRepository
    }