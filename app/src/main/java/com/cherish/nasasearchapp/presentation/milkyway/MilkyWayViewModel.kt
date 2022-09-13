package com.cherish.nasasearchapp.presentation.milkyway

import androidx.lifecycle.ViewModel
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.domain.repository.MilkyWayRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

@HiltViewModel
class MilkyWayViewModel @Inject constructor(private val milkyWayUseCases: MilkyWayRepository): ViewModel() {
    fun getMilkyWayImages() =
        milkyWayUseCases.getMilkyWayImages()
        .catch {
        emit(Resource.Error(it))
        }
//    var getMilkyWayImages = milkyWayUseCases
//        .getMilkyWayImages().map { res ->
//            res.data?.let { MilkyWayItem(it) }
////            when(res){
////                is Resource.Success ->{
////                    res.data?.collection
////                }
////                is Resource.Error -> res.error
////                is Resource.Loading -> res.loading
////            }
//        }
}




// res.data?.let { MilkyWayItem(it) }