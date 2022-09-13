package com.cherish.apimodule.domain.repository

import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.domain.model.MilkyWay
import kotlinx.coroutines.flow.Flow

interface MilkyWayRepository {
    fun getMilkyWayImages(): Flow<Resource<MilkyWay?>>
}