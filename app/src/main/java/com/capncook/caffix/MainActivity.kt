package com.capncook.caffix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capncook.caffix.common.datastore.SessionManager
import com.capncook.caffix.common.network.domain.ConnectivityObserver
import com.capncook.caffix.common.network.domain.GlobalRefreshManager
import com.capncook.caffix.common.ui_components.no_internet_screen.NoInternetScreen
import com.capncook.caffix.common.ui_components.theme.CaffixTheme
import com.capncook.caffix.feature.home.presentation.components.ProductCardNew
import com.capncook.caffix.navigation.AuthGraph
import com.capncook.caffix.navigation.CaffixNavHost
import com.capncook.caffix.navigation.MainAppGraph
import com.capncook.caffix.navigation.OnboardingGraph
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    @Inject
    lateinit var sessionManager: SessionManager

    @Inject
    lateinit var connectivityObserver: ConnectivityObserver

    @Inject
    lateinit var globalRefreshManager: GlobalRefreshManager


    override fun onCreate(savedInstanceState: Bundle?) {

        val splashScreen = installSplashScreen()

        super.onCreate(savedInstanceState)

        val token = sessionManager.getToken()
        val isOnboardingComplete = sessionManager.isOnboardingComplete()

        splashScreen.setKeepOnScreenCondition { false }

        val targetGraph: Any = when {
            token.isNullOrBlank() -> AuthGraph
            !isOnboardingComplete -> OnboardingGraph
            else -> MainAppGraph
        }

        enableEdgeToEdge()

        setContent {
            CaffixTheme {

                val networkStatus by connectivityObserver.observe().collectAsStateWithLifecycle(
                    initialValue = ConnectivityObserver.Status.Available
                )

                val isOffline = networkStatus == ConnectivityObserver.Status.Lost ||
                        networkStatus == ConnectivityObserver.Status.Unavailable

                Box(modifier = Modifier.fillMaxSize()) {

                    CaffixNavHost(targetGraph = targetGraph)

                    if(isOffline) {
                        NoInternetScreen(
                            onTryAgainClick = {
                                globalRefreshManager.triggerRefresh()
                            }
                        )
                    }
                }

            }
        }
    }
}

