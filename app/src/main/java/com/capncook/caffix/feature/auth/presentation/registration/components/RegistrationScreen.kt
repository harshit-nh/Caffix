package com.capncook.caffix.feature.auth.presentation.registration.components

import android.app.Activity
import android.widget.Toast
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
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
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
import com.capncook.caffix.feature.auth.presentation.registration.RegistrationViewModel


@Composable
fun RegistrationScreen(
    viewModel: RegistrationViewModel = hiltViewModel(),
    onNavigationToOtp: (String) -> Unit,
    onNavigationToLogin: () -> Unit
) {


    val state by viewModel.state.collectAsStateWithLifecycle()

    val view = LocalView.current
    val context = view.context as Activity


    LaunchedEffect(state.isSuccess) {
        if(state.isSuccess) {
            onNavigationToOtp(state.phoneNumber)
        }
    }

    LaunchedEffect(state.generalError) {
        state.generalError?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    Scaffold(
        topBar = { RegistrationScreenTopBar() },
        containerColor = colorResource(R.color.account_bg)
    ) { innerPadding ->

        Column(modifier = Modifier
            .padding(innerPadding)
            .padding(16.dp)
            .fillMaxSize()
        ) {

            Text(text = "Let’s Get You Caffeinated",
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                fontSize = 20.sp,
                fontStyle = FontStyle.Italic,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(bottom = 40.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )


            Text(
                text = "Full Name",
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            UserInputText(
                value = state.name,
                onValueChange = { viewModel.onNameChanged(it) },
                placeholderText = "Enter your name",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Done
                ),
                icon = {
                    Icon(
                        painterResource(R.drawable.ic_name),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.DarkGray
                    )
                }
            )


            Spacer(modifier = Modifier.height(20.dp))

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

            UserInputText(
                value = state.phoneNumber,
                onValueChange = { viewModel.onPhoneChanged(it) },
                placeholderText = "Enter your phone number",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Number,
                    imeAction = ImeAction.Next
                ),
                maxLength = 10,
                icon = {
                    Icon(
                        painterResource(R.drawable.ic_phone),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.DarkGray
                    )
                }
            )

            if(state.phoneError != null){
                Text(
                    text = state.phoneError!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Email Address",
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily,
                fontSize = 18.sp,
                color = Color.DarkGray,
                modifier = Modifier
                    .padding(start = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            UserInputText(
                value = state.email,
                onValueChange = { viewModel.onEmailChanged(it) },
                placeholderText = "Enter your email",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                icon = {
                    Icon(
                        painterResource(R.drawable.ic_email),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.DarkGray
                    )
                }
            )

            if(state.emailError != null){
                Text(text = state.emailError!!,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier.padding(start = 16.dp, top = 4.dp)
                )
            }


            Spacer(modifier = Modifier.weight(1f))


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
                enabled = !state.isLoading,
                onClick = {
                    viewModel.registerUser()
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
                normalText = "Already have an account? ",
                linkText = "Log In",
                onLinkClick = {
                    onNavigationToLogin()
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