package com.capncook.caffix.common.network.domain

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject

class GlobalRefreshManager @Inject constructor() {

    private val _refreshEvent = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val refreshEvent = _refreshEvent.asSharedFlow()


    fun triggerRefresh() {
        _refreshEvent.tryEmit(Unit)
    }
}