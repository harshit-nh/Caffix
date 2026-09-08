package com.capncook.caffix.feature.user_onboarding.presentation.final_screen.components

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.user_onboarding.presentation.final_screen.FinalScreenNavigationAction
import com.capncook.caffix.feature.user_onboarding.presentation.final_screen.FinalScreenViewModel


@Composable
fun FinalOnboardingScreen(
    viewModel: FinalScreenViewModel = hiltViewModel(),
    onNavigationHome: () -> Unit,
    onForceLogout: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    val bgColor = colorResource(R.color.account_bg)

    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.celebration)
    )

    val progress by animateLottieCompositionAsState(
        composition = composition,
        iterations = LottieConstants.IterateForever
    )


    LaunchedEffect(state.navigationAction) {

        when(state.navigationAction) {

            FinalScreenNavigationAction.NAVIGATE_TO_HOME -> {
                onNavigationHome()
                viewModel.onNavigationConsumed()
            }

            FinalScreenNavigationAction.NAVIGATE_TO_LOGIN -> {
                onForceLogout()
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
            FinalScreenTopBar(
                currentStep = 5
            )
        },
        containerColor = bgColor

    ) { innerPadding ->

        Box(
            modifier = Modifier
                .padding(innerPadding)
                .padding(top = 20.dp)
                .fillMaxSize()
        ) {

            LottieAnimation(
                composition = composition,
                progress = { progress },
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )


            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(horizontal = 24.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {


                Spacer(modifier = Modifier.height(40.dp))

                AnimatedFinalCard()

                Spacer(modifier = Modifier.height(32.dp))


                Text(
                    text = "You're All Set!",
                    fontFamily = poppinsFontFamily,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.Black
                )


                Spacer(modifier = Modifier.height(16.dp))


                Text(
                    text = "Your coffee journey starts here. Your recommendations will get better with every sip.",
                    fontFamily = poppinsFontFamily,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color.DarkGray,
                    textAlign = TextAlign.Center,
                    lineHeight = 22.sp,
                    modifier = Modifier.padding(horizontal = 16.dp)
                )


                Spacer(modifier = Modifier.height(50.dp))


                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = CoffeeBrown,
                        contentColor = Color.White
                    ),
                    enabled = !state.isLoading,
                    onClick = {
                        viewModel.completeOnBoarding()
                    }
                ) {

                    if (state.isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.size(24.dp),
                            color = Color.White,
                            strokeWidth = 2.dp
                        )
                    } else {
                        Text(
                            text = "Explore Caffix →",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp
                        )
                    }

                }


            }


        }

    }
}