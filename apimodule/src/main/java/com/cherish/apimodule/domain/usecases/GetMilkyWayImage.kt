package com.cherish.apimodule.domain.usecases

import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.domain.model.MilkyWay
import com.cherish.apimodule.domain.repository.MilkyWayRepository
import kotlinx.coroutines.flow.Flow


class GetMilkyWayImage(
    private val repository: MilkyWayRepository
) {
    operator fun invoke(): Flow<Resource<MilkyWay?>> {
        return repository.getMilkyWayImages()
    }
}