package com.capncook.caffix.common.datastore

interface SessionManager {

    fun saveToken(token: String)
    fun getToken(): String?
    fun saveOnboardingState(isComplete: Boolean)
    fun isOnboardingComplete(): Boolean
    fun clearSession()
}


