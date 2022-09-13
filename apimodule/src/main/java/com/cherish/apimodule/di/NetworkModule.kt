package com.cherish.apimodule.di

import android.content.Context
import com.cherish.apimodule.BuildConfig
import com.cherish.apimodule.common.Constant.REQUEST_TIMEOUT_DURATION
import com.cherish.apimodule.common.IODispatcher
import com.cherish.apimodule.common.NetworkConnectionInterceptor
import com.cherish.apimodule.data.remote.MilkyWayApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class NetworkModule {
    open var baseUrl = BuildConfig.BASE_URL

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        }
        return OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .addInterceptor(NetworkConnectionInterceptor(context))
            .callTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT_DURATION, TimeUnit.SECONDS)
            .build()

    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideMilkWayService(retrofit: Retrofit): MilkyWayApi =
        retrofit.create(MilkyWayApi::class.java)

    @IODispatcher
    @Provides
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO
}