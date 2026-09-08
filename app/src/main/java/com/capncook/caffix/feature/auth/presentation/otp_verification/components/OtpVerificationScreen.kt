package com.capncook.caffix.feature.auth.presentation.otp_verification.components

import android.app.Activity
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.CoffeeBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily
import com.capncook.caffix.feature.auth.presentation.otp_verification.OtpNavigationAction
import com.capncook.caffix.feature.auth.presentation.otp_verification.OtpVerificationViewModel
import com.capncook.caffix.feature.auth.presentation.registration.components.InlineTextLink


@Composable
fun OtpVerificationScreen(
    viewModel: OtpVerificationViewModel = hiltViewModel(),
    onNavigationToHome: () -> Unit,
    onNavigationToOnboarding: () -> Unit,
    onNavigationToLogin: () -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    val view = LocalView.current
    val context = view.context as Activity


    LaunchedEffect(state.isSuccess) {
        when (state.navigationAction) {
            OtpNavigationAction.NAVIGATE_TO_HOME -> {
                onNavigationToHome()
            }

            OtpNavigationAction.NAVIGATE_TO_ONBOARDING -> {
                onNavigationToOnboarding()
            }
            null -> {
                //DO nothing
            }
        }
    }


    LaunchedEffect(state.generalError) {

        if(!state.generalError.isNullOrBlank()) {
            Toast.makeText(context, state.generalError, Toast.LENGTH_SHORT).show()
        }

    }

//    LaunchedEffect(state.otpError) {
//
//        if(!state.otpError.isNullOrBlank()) {
//            Toast.makeText(context, state.otpError, Toast.LENGTH_SHORT).show()
//        }
//
//    }

    LaunchedEffect(state.resendMessage) {

        if(!state.resendMessage.isNullOrBlank()) {
            Toast.makeText(context, state.resendMessage, Toast.LENGTH_SHORT).show()
            viewModel.clearResendMessage()
        }
    }



    val focusRequesters = remember { List(6) { FocusRequester() } }

    LaunchedEffect(Unit) {
        focusRequesters[0].requestFocus()
    }


    val maskedPhone = remember(viewModel.phoneNumber) {

        if(viewModel.phoneNumber.length >= 10){
            viewModel.phoneNumber.take(4) + "****" + viewModel.phoneNumber.takeLast(3)
        }else{
            viewModel.phoneNumber
        }
    }



    Scaffold(
        topBar = { OtpVerificationScreenTopBar() },
        containerColor = colorResource(R.color.account_bg)
    ) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()
        ) {


            Text(text = "Enter the 6-digit code we sent to",
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                fontSize = 16.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(top = 26.dp, bottom = 8.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )


            Text(text = maskedPhone,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                color = Color.Black,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )


            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ){

                repeat(6) { index ->

                    val char = state.otpCode.getOrNull(index)?.toString() ?: ""

                    OtpInputField(
                        value = char,
                        onValueChange = { newValue ->

                            val newOtp = state.otpCode.toMutableList()

                            if(newValue.isNotEmpty()) {

                                val typedChar = newValue.last()


                                if(index < newOtp.size) newOtp[index] = typedChar
                                else newOtp.add(typedChar)

                                viewModel.onOtpChange(newOtp.joinToString(""))

                                if(index < 5) focusRequesters[index + 1].requestFocus()

                            }else{
                                if(index < newOtp.size) newOtp.removeAt(index)

                                viewModel.onOtpChange(newOtp.joinToString(""))
                            }

                            viewModel.onOtpChange(newOtp.joinToString(""))
                        },
                        modifier = Modifier
                            .weight(1f)
                            .focusRequester(focusRequesters[index]),
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number,
                            imeAction = if(index == 5) ImeAction.Done else ImeAction.Next
                        ),
                        maxLength = 1
                    )

                }

            }


            if(state.otpError != null){
                Text(
                    text = state.otpError!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }



            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp, bottom = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                if(state.isResendEnabled) {

                    InlineTextLink(
                        normalText = "",
                        linkText = "Resend Code",
                        onLinkClick = {
                            viewModel.resendOtp()
                        },
                        style = TextStyle(
                            fontSize = 16.sp,
                            lineHeight = 24.sp,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.Medium,
                            color = CoffeeBrown
                        ),
                        modifier = Modifier
                            .padding(bottom = 28.dp)

                    )


                }else{

                    val formattedTime = String.format("%02d", state.timerValue)
                    Text(
                        text = "Resend code in ${formattedTime}s",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        fontSize = 15.sp,
                        color = Color.DarkGray,
                        textAlign = TextAlign.Center
                    )

                }

            }


            if(viewModel.isFromLogin) {
                InlineTextLink(
                    normalText = "",
                    linkText = "Change Number",
                    onLinkClick = {
                        onNavigationToLogin()
                    },
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 24.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Medium
                    ),
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                        .padding(bottom = 28.dp)

                )

            }else{

                Spacer(modifier = Modifier.height(52.dp))
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeeBrown,
                    contentColor = Color.White
                ),
                enabled = !state.isLoading && state.otpCode.length == 6,
                onClick = {
                    viewModel.verifyOtp()
                }
            ) {
                if(state.isLoading){
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = Color.White,
                        strokeWidth = 2.dp
                    )
                }else {
                    Text(
                        text = "Verify",
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }

        }
    }


}