package com.capncook.caffix

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.capncook.caffix.common.datastore.SessionManager
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

                CaffixNavHost(targetGraph = targetGraph)

            }
        }
    }
}

