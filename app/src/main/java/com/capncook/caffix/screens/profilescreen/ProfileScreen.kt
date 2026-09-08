package com.capncook.caffix.screens.profilescreen

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.HelpCenter
import androidx.compose.material.icons.automirrored.filled.ReceiptLong
import androidx.compose.material.icons.filled.CreditCard
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Newspaper
import androidx.compose.material.icons.filled.NotificationsNone
import androidx.compose.material.icons.filled.Policy
import androidx.compose.material.icons.filled.WbSunny
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.view.WindowCompat
import androidx.navigation.NavHostController
import com.capncook.caffix.R
import com.capncook.caffix.navigation_bottom.MyBottomNavBar
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun ProfileScreen(navController: NavHostController) {

    val view = LocalView.current
    val context = view.context as Activity

    DisposableEffect(Unit) {
        val window = context.window
        val controller = WindowCompat.getInsetsController(window, view)

        // Store the original state if you want to revert
        val originalState = controller.isAppearanceLightStatusBars

        //Force icons to black
        controller.isAppearanceLightStatusBars = true

        onDispose {
            //Revert to what it was before when leaving the screen
            controller.isAppearanceLightStatusBars = originalState
        }
    }


    var premiumProgress by remember { mutableFloatStateOf(0.4f) }
    val actualValue = (premiumProgress * 10).toInt()


    Scaffold(
        containerColor = Color.White,
        bottomBar = { MyBottomNavBar(navController, "Profile") }

    ) { innerPadding ->

        LazyColumn {

            item{

                Column(modifier = Modifier
                    .padding(innerPadding)
                    .padding(horizontal = 16.dp)
                    .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Spacer(modifier = Modifier.height(30.dp))

                    Image(painter = painterResource(R.drawable.profile_image),
                        "Profile Image",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                            .border(1.dp, Color.Gray.copy(alpha = 0.7f), CircleShape)
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Text(text = "Harshit Sharma",
                        color = Color.DarkGray,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(120.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = listOf(
                                        Color(0xFFFDFBF9),
                                        Color(0xFFF2E4DC)
                                    )
                                ),
                                shape = RoundedCornerShape(20.dp)
                            ),
                        colors = CardDefaults.cardColors(
                            containerColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(20.dp)
                    ) {

                        Row(modifier = Modifier.fillMaxSize(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {

                            Icon(painter = painterResource(R.drawable.premium_icon),
                                "Premium User Icon",
                                tint = LightBrown,
                                modifier = Modifier.padding(horizontal = 16.dp).size(48.dp)
                            )


                            Column(verticalArrangement = Arrangement.SpaceBetween,
                                modifier = Modifier.weight(1f)) {

                                Text(text = "Gold Member",
                                    color = Color.DarkGray,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.SemiBold,
                                    fontSize = 18.sp
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                LinearProgressIndicator(
                                    progress = { premiumProgress },
                                    modifier = Modifier
                                        .height(10.dp)
                                        .fillMaxWidth()
                                        .clip(RoundedCornerShape(4.dp)),
                                    color = Color(0xFFC67C4E),
                                    trackColor = Color(0xFFEBD9CE),
                                    strokeCap = StrokeCap.Round
                                )

                                Spacer(modifier = Modifier.height(5.dp))

                                Text(text = "$actualValue/10 stars for your next free coffee",
                                    color = Color.DarkGray,
                                    fontFamily = poppinsFontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontSize = 12.sp
                                )

                            }


                            Text(text = "$actualValue/10",
                                color = LightBrown,
                                fontFamily = poppinsFontFamily,
                                fontWeight = FontWeight.SemiBold,
                                modifier = Modifier.align(Alignment.Top).padding(top = 15.dp, end = 15.dp)
                            )

                        }

                    }


                    Text(text = "Account Settings",
                        color = Color.DarkGray,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start).padding(top = 30.dp, bottom = 20.dp)
                    )


                    AccountSettingsCard(icon = Icons.AutoMirrored.Default.ReceiptLong, title = "My Orders", description = "Orders")

                    Spacer(modifier = Modifier.height(5.dp))

                    AccountSettingsCard(icon = Icons.Default.LocationOn, title = "Saved Addresses", description = "Address")

                    Spacer(modifier = Modifier.height(5.dp))

                    AccountSettingsCard(icon = Icons.Default.CreditCard, title = "Payment Methods", description = "Payment")

                    Spacer(modifier = Modifier.height(5.dp))

                    AccountSettingsCard(icon = Icons.Default.FavoriteBorder, title = "Wishlist", description = "Wishlist")



                    Text(text = "Preferences",
                        color = Color.DarkGray,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start).padding(top = 30.dp, bottom = 20.dp)
                    )


                    PreferencesCard(icon = Icons.Default.WbSunny, title = "Dark Mode", description = "Theme")

                    Spacer(modifier = Modifier.height(5.dp))

                    PreferencesCard(icon = Icons.Default.NotificationsNone, title = "Push Notifications", description = "Notifications")



                    Text(text = "Support & Legal",
                        color = Color.DarkGray,
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 20.sp,
                        modifier = Modifier.align(Alignment.Start).padding(top = 30.dp, bottom = 20.dp)
                    )


                    AccountSettingsCard(icon = Icons.AutoMirrored.Default.HelpCenter, title = "Help & Support", description = "Help")

                    Spacer(modifier = Modifier.height(5.dp))

                    AccountSettingsCard(icon = Icons.Default.Policy, title = "Privacy Policy", description = "Policy")

                    Spacer(modifier = Modifier.height(5.dp))

                    AccountSettingsCard(icon = Icons.Default.Newspaper, title = "Terms & Conditions", description = "TnC")




                }

            }

        }


    }

}