package com.cherish.apimodule.di

import com.cherish.apimodule.data.repository.MilkyWayRepositoryImpl
import com.cherish.apimodule.domain.repository.MilkyWayRepository
import com.cherish.apimodule.domain.usecases.GetMilkyWayImage
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object  MilkyWayModule {
    @Provides
    @Singleton
     fun provideMilkyWayRepository(milkyWayRepository: MilkyWayRepositoryImpl): MilkyWayRepository = milkyWayRepository

    @Provides
    @Singleton
    fun provideMilkyWayUseCase(repository: MilkyWayRepository): GetMilkyWayImage = GetMilkyWayImage(repository)

}