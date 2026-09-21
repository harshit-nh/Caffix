package com.capncook.caffix.feature.home.domain.location

import com.capncook.caffix.common.Resource

interface LocationTracker {

    suspend fun getCurrentAddress(): Resource<String>
}