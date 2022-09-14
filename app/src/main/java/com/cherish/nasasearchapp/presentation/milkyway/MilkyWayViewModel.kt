package com.cherish.nasasearchapp.presentation.milkyway

import androidx.lifecycle.ViewModel
import com.cherish.apimodule.domain.usecases.GetMilkyWayImage
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MilkyWayViewModel @Inject constructor(
    private val getMilkyWayImage: GetMilkyWayImage
    ) : ViewModel() {

     fun getMilkyWayImages() = getMilkyWayImage.invoke()

}