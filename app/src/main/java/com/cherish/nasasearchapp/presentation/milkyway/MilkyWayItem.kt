package com.cherish.nasasearchapp.presentation.milkyway

import android.os.Parcelable
import com.cherish.nasasearchapp.common.BaseViewState
import kotlinx.parcelize.Parcelize


@Parcelize
data class MilkyWayItem(
    val id: String?,
    val title: String?,
    var center: String?,
    val date: String?,
    val image: String?,
    val description: String?
) : BaseViewState(), Parcelable