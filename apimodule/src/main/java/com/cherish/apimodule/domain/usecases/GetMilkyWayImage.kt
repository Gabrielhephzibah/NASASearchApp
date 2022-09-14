package com.cherish.apimodule.domain.usecases

import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.domain.model.MilkyWay
import com.cherish.apimodule.domain.repository.MilkyWayRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetMilkyWayImage @Inject constructor(
    private val repository: MilkyWayRepository
) {
    operator fun invoke(): Flow<Resource<MilkyWay?>> {
        return repository.getMilkyWayImages()
    }
}