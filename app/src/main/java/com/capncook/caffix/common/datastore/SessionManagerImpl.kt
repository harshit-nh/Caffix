package com.capncook.caffix.common.datastore

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.core.content.edit

class SessionManagerImpl @Inject constructor(
    @ApplicationContext context: Context
) : SessionManager {


    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()


    private val sharedPrefs = EncryptedSharedPreferences.create(
        context,
        "secure_auth_prefs",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )



    override fun saveToken(token: String) {
        sharedPrefs.edit { putString("JWT_TOKEN", token) }
    }

    override fun getToken(): String? {
        return sharedPrefs.getString("JWT_TOKEN", null)
    }

    override fun saveOnboardingState(isComplete: Boolean) {
        sharedPrefs.edit { putBoolean("IS_ONBOARDING_COMPLETE", isComplete) }
    }

    override fun isOnboardingComplete(): Boolean {
        return sharedPrefs.getBoolean("IS_ONBOARDING_COMPLETE", false)
    }

    override fun clearSession() {
        sharedPrefs.edit { clear() }
    }


}