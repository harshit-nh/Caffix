package com.capncook.caffix.feature.auth.presentation.login.components

import android.app.Activity
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
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
import com.capncook.caffix.feature.auth.presentation.login.LoginViewModel
import com.capncook.caffix.feature.auth.presentation.registration.components.InlineTextLink


@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    onNavigationToOtp: (String) -> Unit,
    onNavigationToRegister: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    val view = LocalView.current
    val context = view.context as Activity


    LaunchedEffect(state.isSuccess) {
        if(state.isSuccess) {
            onNavigationToOtp(state.phoneNumber)
        }
    }



    Scaffold(
        topBar = { LoginScreenTopBar() },
        containerColor = colorResource(R.color.account_bg)
    ) { innerPadding ->


        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()
        ) {



            Text(
                text = "Your daily fix awaits",
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )



            Text(
                text = "Phone Number",
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            LoginInputField(
                value = state.phoneNumber,
                onValueChange = { viewModel.onPhoneChanged(it) },
                placeholderText = "9990000000",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Phone,
                    imeAction = ImeAction.Done
                ),
                maxLength = 10,
                showCountryCode = true
            )


            if(state.phoneError != null){
                Text(
                    text = state.phoneError!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }


            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp, end = 16.dp, top = 60.dp, bottom = 20.dp)
                    .height(50.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = CoffeeBrown,
                    contentColor = Color.White
                ),
                enabled = !state.isLoading,
                onClick = {
                    viewModel.requestOtp()
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
                        text = "Continue →",
                        fontSize = 18.sp,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.Normal,
                        color = Color.White
                    )
                }
            }



            InlineTextLink(
                normalText = "Don't have an account? ",
                linkText = "Create Account",
                onLinkClick = {
                    onNavigationToRegister()
                },
                style = TextStyle(
                    fontSize = 16.sp,
                    lineHeight = 24.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.SemiBold
                ),
                normalTextSpanStyle = SpanStyle(
                    color = Color.Gray,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal,
                    fontSize = 14.sp
                ),
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 28.dp)
            )

        }

    }

}