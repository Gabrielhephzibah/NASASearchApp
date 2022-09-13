package com.cherish.nasasearchapp.presentation.milkyway

import android.content.Context
import com.cherish.apimodule.common.Resource
import com.cherish.nasasearchapp.R
import com.cherish.nasasearchapp.common.BaseViewState
import retrofit2.HttpException

class MilkyWayViewState<T>(
    private val loadState: Resource<T>
): BaseViewState() {
    fun getProgressBarVisibility() = getViewVisibility(isVisible = loadState is Resource.Loading)

    fun getRecyclerViewVisibility() = getViewVisibility(isVisible = loadState !is Resource.Loading)

    fun getErrorVisibility() = getViewVisibility(isVisible = loadState is Resource.Error)

    //fun getEmptyListVisibility() = getViewVisibility()

    fun getErrorMessage(context: Context)  = if (loadState is Resource.Error) {

        if (loadState.error is HttpException) {
            when ((loadState.error as HttpException).code()) {
                500, 502, 503, 504 -> "Server Error, please try again later"
                404 -> "The request resource does not exist"
                400 -> "Bad Request, Please try again later"
                else -> context.getString(R.string.error)
            }
        } else context.getString(R.string.error)

}else{
    ""
}
}
