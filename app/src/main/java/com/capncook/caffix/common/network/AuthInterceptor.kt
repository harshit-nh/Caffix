package com.capncook.caffix.common.network

import com.capncook.caffix.common.datastore.SessionManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthInterceptor @Inject constructor(
    private val sessionManager: SessionManager
) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest = chain.request()

        val isAuthEndpoint = originalRequest.url.encodedPath.contains("/api/auth/")

        if(isAuthEndpoint){
            return chain.proceed(originalRequest)
        }

        val token = sessionManager.getToken()

        val requestBuilder = originalRequest.newBuilder()
        if(!token.isNullOrBlank()){
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        return chain.proceed(requestBuilder.build())
    }
}