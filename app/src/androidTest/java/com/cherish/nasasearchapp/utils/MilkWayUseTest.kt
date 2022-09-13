package com.cherish.nasasearchapp.utils

import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.data.repository.MilkyWay
import kotlinx.coroutines.flow.Flow

interface MilkWayUseTest {
    fun getMilkyWayImages(): Flow<Resource<MilkyWay?>>
}