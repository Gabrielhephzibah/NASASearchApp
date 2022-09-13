package com.cherish.apimodule.domain.usecases

import com.cherish.apimodule.common.Constant
import com.cherish.apimodule.common.IODispatcher
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.data.remote.MilkyWayApi
import com.cherish.apimodule.domain.model.MilkyWay
import com.cherish.apimodule.domain.repository.MilkyWayRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MilkyWayUseCasesImpl @Inject constructor(
    private val milkyWayService: MilkyWayApi,
    @IODispatcher private val IODispatcher: CoroutineDispatcher
) : MilkyWayRepository {

    override fun getMilkyWayImages(): Flow<Resource<MilkyWay?>>  = flow<Resource<MilkyWay?>> {
        val response = milkyWayService.getMilkyWayImages(
            Constant.SEARCH_QUERY,
            Constant.MEDIA_TYPE_QUERY,
            Constant.YEAR_START_QUERY,
            Constant.YEAR_END_QUERY
        )
        emit(Resource.Loading(false))
        emit(Resource.Success(response))
    }.flowOn(IODispatcher)
        .catch {
            emit(Resource.Loading(false))
            emit(Resource.Error(it))
        }
}



