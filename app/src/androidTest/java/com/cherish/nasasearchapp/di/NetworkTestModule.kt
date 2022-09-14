package com.cherish.nasasearchapp.di

import com.cherish.apimodule.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces =  [NetworkModule::class]
)
class NetworkTestModule: NetworkModule() {

    override var baseUrl: String = "http://localhost:8080/"
}