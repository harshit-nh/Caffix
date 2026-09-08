package com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.components

import android.Manifest
import android.os.Build
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.snackbar.CaffixSnackBar
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.common.utils.isNotificationPermissionGranted
import com.capncook.caffix.common.utils.openAppNotificationSettings
import com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.NotificationPermsNavigationAction
import com.capncook.caffix.feature.user_onboarding.presentation.notification_prefs.NotificationPermsViewModel
import kotlinx.coroutines.launch


@Composable
fun NotificationPermsScreen(
    viewModel: NotificationPermsViewModel = hiltViewModel(),
    onNavigateNext: () ->  Unit,
    onForceLogout: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current

    val snackBarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    // We need to remember which option ID the user just clicked so we can toggle it AFTER permission is granted
    var pendingOptionId by remember { mutableStateOf<String?>(null) }



    DisposableEffect(lifecycleOwner) {

        val observer = LifecycleEventObserver { _, event ->
            if(event == Lifecycle.Event.ON_RESUME) {
                viewModel.syncSystemPermissionState(context.isNotificationPermissionGranted())
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }


    val permissionLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.RequestPermission()
    ) { isGranted ->

        pendingOptionId?.let { id ->

            if(isGranted) {
                viewModel.onToggleOption(id, isGranted = true)
            }else{

                scope.launch {
                    val result = snackBarHostState.showSnackbar(
                        message = "Notification permission denied",
                        actionLabel = "Settings",
                        duration = SnackbarDuration.Long
                    )

                    if(result == SnackbarResult.ActionPerformed) {
                        context.openAppNotificationSettings()
                    }
                }
            }
            pendingOptionId = null
        }
    }


    LaunchedEffect(state.navigationAction) {
        when (state.navigationAction) {
            NotificationPermsNavigationAction.NAVIGATE_TO_NEXT -> {
                onNavigateNext()
                viewModel.onNavigationConsumed()
            }
            NotificationPermsNavigationAction.NAVIGATE_TO_LOGIN -> {
                onForceLogout()
                viewModel.onNavigationConsumed()
            }
            null -> Unit
        }
    }


    Scaffold(
        snackbarHost = {
            SnackbarHost( hostState = snackBarHostState ) { snackBarData ->
                CaffixSnackBar(snackBarData = snackBarData)
            }
        },
        topBar = {
            NotificationPrefsTopBar(
                currentStep = 3
            )
        },
        bottomBar = {
            NotificationPermsBottomBar(
                isLoading = state.isLoading,
                onNextClick = {
                    viewModel.onNextClick()
                },
                onSkipClick = {
                    viewModel.onSkipClick()
                }
            )
        },
        containerColor = colorResource(R.color.account_bg)

    ) { innerPadding ->


        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp)
        ) {


            Text(
                text = "Stay Updated",
                fontFamily = poppinsFontFamily,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                color = Color.DarkGray,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                textAlign = TextAlign.Center
            )

            Text(
                text = "Get only the news you want",
                color = Color.DarkGray,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 14.dp),
                textAlign = TextAlign.Center
            )


            Spacer(modifier = Modifier.height(32.dp))


            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {

                state.options.forEach { option ->

                    NotificationPrefsCard(
                        icon = option.iconId,
                        title = option.title,
                        description = option.description,
                        checked = option.isEnabled,
                        onCheckedChange = { _ ->

                            // If it requires system permission and they are trying to turn it ON
                            if(option.requiresSystemPermission && !option.isEnabled) {

                                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                                    pendingOptionId = option.id
                                    permissionLauncher.launch(Manifest.permission.POST_NOTIFICATIONS)
                                }else {

                                    viewModel.onToggleOption(option.id, isGranted = true )
                                }

                            }else {
                                // Normal toggle for items that don't need OS permission
                                viewModel.onToggleOption(option.id, isGranted = true )
                            }
                        }
                    )
                }

            }

        }

    }

}