package com.cherish.nasasearchapp.presentation.milkywaydetails

import androidx.core.text.HtmlCompat
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.cherish.nasasearchapp.common.extension.convertDate
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MilkyWayDetailViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    var detailArgs = MilkyWayDetailFragmentArgs.fromSavedStateHandle(savedStateHandle)
    var title = detailArgs.milkyWayItem?.title
    var center = detailArgs.milkyWayItem?.center
    var image = detailArgs.milkyWayItem?.image
    var date = detailArgs.milkyWayItem?.date?.convertDate()
    var description = detailArgs.milkyWayItem?.description?.let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
}