package com.cherish.apimodule.data.remote

import com.cherish.apimodule.domain.model.MilkyWay
import retrofit2.http.GET
import retrofit2.http.Query

interface MilkyWayApi {
    @GET("search")
    suspend fun getMilkyWayImages(
        @Query("q",encoded = true) q: String,
        @Query("media_type") mediaType: String,
        @Query("year_start") yearStart: String,
        @Query("year_end") yearEnd: String,
    ): MilkyWay
}