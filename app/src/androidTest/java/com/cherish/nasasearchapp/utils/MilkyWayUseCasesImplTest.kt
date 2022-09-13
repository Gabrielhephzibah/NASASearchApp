package com.cherish.nasasearchapp.utils

import com.cherish.apimodule.common.IODispatcher
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.data.repository.MilkyWay
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MilkyWayUseCasesImplTest @Inject constructor(
    private val milkyWayServiceTest: TestService,
    @IODispatcher private val IODispatcher: CoroutineDispatcher
): MilkWayUseTest {
    override fun getMilkyWayImages(): Flow<Resource<MilkyWay?>> = flow {
        val response = milkyWayServiceTest.getMilkyWayImages()
        emit(Resource.Loading(false))
        emit(Resource.Success(response))
    }.flowOn(IODispatcher)
        .catch {
            emit(Resource.Loading(false))
            emit(Resource.Error(it)) }

}