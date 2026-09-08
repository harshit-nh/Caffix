package com.capncook.caffix.screens.welcomescreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun WelcomeScreen(onNavigateToRegister: () -> Unit) {

    Box(modifier = Modifier.fillMaxSize()
        .background(color = Color.Black)
    ){
        Image(
            painter = painterResource(R.drawable.image_splash),
            contentDescription = "Welcome Image"
        )


        Column(modifier = Modifier.fillMaxSize().padding(vertical = 70.dp, horizontal = 25.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Bottom) {

            Text(text = "Fall in love with coffee",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center
            )



            Text(text = "in blissful delight!",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 28.sp,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(15.dp))

            Text(text = "Welcome to our cozy Coffee Corner, where every cup is a delight for you.",
                color = Color.LightGray,
                fontSize = 18.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = poppinsFontFamily,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(50.dp))


            Button(
                modifier = Modifier.fillMaxWidth().height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBrown,
                    contentColor = Color.White
                ),
                onClick = {
                    onNavigateToRegister()
                }
            ) {
                Text(text = "Get started",
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal)
            }
        }
    }
}