package com.cherish.nasasearchapp

import com.cherish.apimodule.di.NetworkModule
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
//@InstallIn(SingletonComponent::class)
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [NetworkModule::class]
)
class TestModule: NetworkModule() {

    override var baseUrl: String = "http://localhost:8080/"


//   @Provides
//  @Singleton
//  fun provideUrl(): String = "http://localhost:8080/"
//
//    @Provides
//    @Singleton
//    fun provideOkHttpClient() : OkHttpClient {
//        return OkHttpClient.Builder()
//            .connectTimeout(30, TimeUnit.SECONDS)
//            .writeTimeout(30, TimeUnit.SECONDS)
//            .readTimeout(30, TimeUnit.SECONDS)
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//        return Retrofit.Builder()
//            .baseUrl(MockWebServer().url("/"))
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideMilkyWayUseCase(milkyWayUseCases: MilkyWayUseCasesImpl): MilkyWayUseCases = milkyWayUseCases
//
//
//    @Provides
//    @Singleton
//    fun provideMilkWayService(retrofit: Retrofit): MilkyWayService = retrofit.create(MilkyWayService::class.java)
//
//    @IODispatcher
//    @Provides
//    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}