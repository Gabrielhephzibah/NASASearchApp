package com.cherish.nasasearchapp.presentation.milkyway

import android.content.Context
import com.cherish.apimodule.common.Resource
import com.cherish.nasasearchapp.R
import com.cherish.nasasearchapp.common.BaseViewState
import retrofit2.HttpException

class MilkyWayViewState<T>(
    private val viewState: Resource<T>
) : BaseViewState() {
    fun getProgressBarVisibility() = getViewVisibility(isVisible = viewState is Resource.Loading)

    fun getRecyclerViewVisibility() = getViewVisibility(isVisible = viewState !is Resource.Loading)

    fun getErrorVisibility() = getViewVisibility(isVisible = viewState is Resource.Error)

    fun getErrorMessage(context: Context) = if (viewState is Resource.Error) {
        if (viewState.error is HttpException) {
            when ((viewState.error as HttpException).code()) {
                500, 502, 503, 504 -> context.getString(R.string.server_error)
                404 -> context.getString(R.string.not_found)
                400 -> context.getString(R.string.bad_request)
                else -> context.getString(R.string.error)
            }
        } else context.getString(R.string.error)
    } else {
        ""
    }
}
