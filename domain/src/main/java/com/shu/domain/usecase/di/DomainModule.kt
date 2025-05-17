package com.shu.domain.usecase.di

import com.shu.domain.usecase.GetOffersUseCase
import com.shu.entity.repository.Repository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@InstallIn(ViewModelComponent::class)
@Module
class DomainModule {

    @Provides
    fun provideGetOffersUseCase(repository: Repository): GetOffersUseCase {
        return GetOffersUseCase(repository)
    }
}