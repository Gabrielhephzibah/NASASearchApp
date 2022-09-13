package com.cherish.nasasearchapp.utils

import com.cherish.apimodule.data.repository.MilkyWay
import retrofit2.http.GET

interface TestService {
    @GET("search")
    suspend fun getMilkyWayImages(
    ): MilkyWay
}