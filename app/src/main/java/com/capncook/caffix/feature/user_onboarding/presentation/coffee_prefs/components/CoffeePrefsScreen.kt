package com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs.components

import android.widget.Toast
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs.CoffeePrefsNavigationAction
import com.capncook.caffix.feature.user_onboarding.presentation.coffee_prefs.CoffeePrefsViewModel


@Composable
fun CoffeePrefsScreen(
    viewModel: CoffeePrefsViewModel = hiltViewModel(),
    onNavigateNext: () -> Unit,
    onForceLogout: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current


    LaunchedEffect(state.navigationAction) {

        when(state.navigationAction) {

            CoffeePrefsNavigationAction.NAVIGATE_TO_LOGIN -> {
                onForceLogout()
                viewModel.onNavigationConsumed()
            }

            CoffeePrefsNavigationAction.NAVIGATE_TO_NEXT -> {
                onNavigateNext()
                viewModel.onNavigationConsumed()
            }
            null -> Unit
        }
    }


    LaunchedEffect(state.errorMessage) {

        if(state.errorMessage != null) {
            Toast.makeText(context, state.errorMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    Scaffold(
        topBar = {
            CoffeePrefsTopBar(
                currentStep = 2
            )
        },
        bottomBar = {
            CoffeePrefsBottomBar(
                isNextEnabled = state.selectedCoffeeId != null,
                isLoading = state.isLoading,
                onNextClick = { viewModel.onNextClick() },
                onSkipClick = { viewModel.onSkipClick() }
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
                text = "Caffeine Kick?",
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
                text = "How do you like your energy boost?",
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

                state.coffeeOptions.forEach { option ->
                    CoffeePrefCard(
                        icon = option.iconId,
                        title = option.title,
                        description = option.description,
                        isSelected = state.selectedCoffeeId == option.id,
                        onClick = { viewModel.coffeeSelected(option.id) }
                    )
                }
            }


        }
    }
}