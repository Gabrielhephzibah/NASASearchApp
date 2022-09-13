package com.cherish.nasasearchapp.common

import android.view.View

open class BaseViewState {
    fun getViewVisibility(isVisible: Boolean) = if (isVisible) View.VISIBLE else View.GONE
}