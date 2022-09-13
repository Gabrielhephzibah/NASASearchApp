package com.cherish.apimodule.di

import com.cherish.apimodule.domain.repository.MilkyWayRepository
import com.cherish.apimodule.domain.usecases.MilkyWayUseCasesImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    abstract fun provideMilkyWayUseCase(milkyWayUseCases: MilkyWayUseCasesImpl): MilkyWayRepository

}