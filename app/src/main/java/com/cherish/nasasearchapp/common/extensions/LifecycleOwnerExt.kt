package com.cherish.nasasearchapp.common.extensions

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch

fun <T> LifecycleOwner.collect(
    flow: Flow<T>,
    action: suspend (T) -> Unit,
    display: suspend (T) -> Unit
) {
   lifecycleScope.launch{
        repeatOnLifecycle(Lifecycle.State.STARTED) {
            flow.collect {
                action.invoke(it)
                display.invoke(it)
            }
        }
    }
}



