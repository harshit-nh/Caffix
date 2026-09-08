package com.capncook.caffix.screens.cartscreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.R
import com.capncook.caffix.common.ui_components.theme.LightBrown
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun PaymentModeSelectionCard(totalAmount: Int) {

    var expanded by remember { mutableStateOf(false) }
    var selectedMode by remember { mutableStateOf("Online") }

    val paymentModes = listOf("Online", "Cash on Delivery")


    Card(modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = 0.7f)
        )) {

        Column(modifier = Modifier.padding(16.dp)) {

            Row(modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Row(verticalAlignment = Alignment.CenterVertically) {

                    Icon(
                        painter = painterResource(
                            id = if (selectedMode == "Online") R.drawable.mobile_banking
                            else R.drawable.wallet
                        ),
                        contentDescription = "Payment Mode",
                        modifier = Modifier.size(38.dp),
                        tint = LightBrown
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column() {

                        Text(text = selectedMode,
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            color = Color.Black
                        )

                        Text(text = "₹$totalAmount",
                            fontFamily = poppinsFontFamily,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 16.sp,
                            color = LightBrown
                        )
                    }

                }

                Box() {

                    Icon(painter = painterResource(R.drawable.regular_outline_arrow_down),
                        "Payment Methods",
                        modifier = Modifier
                            .size(28.dp)
                            .clickable { expanded = true },
                        tint = Color.Black
                    )


                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false },
                        modifier = Modifier.background(Color.White)
                    ) {

                        paymentModes.forEach { mode ->
                            DropdownMenuItem(
                                text = {
                                    Text(text = mode,
                                        fontFamily = poppinsFontFamily,
                                        fontWeight = FontWeight.Medium,
                                        fontSize = 15.sp,
                                        color = Color.Black
                                    )
                                }, onClick = {
                                    selectedMode = mode
                                    expanded = false
                                },
                                leadingIcon = {
                                    Icon(
                                        painter = painterResource(
                                            if (mode == "Online") R.drawable.mobile_banking
                                            else R.drawable.wallet
                                        ),
                                        contentDescription = null,
                                        modifier = Modifier.size(30.dp),
                                        tint = LightBrown

                                    )
                                }
                            )
                        }
                    }
                }

            }

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = LightBrown,
                    contentColor = Color.White
                ),
                onClick = {

                }
            ) {
                Text(text = "Place Order",
                    fontSize = 18.sp,
                    fontFamily = poppinsFontFamily,
                    fontWeight = FontWeight.Normal)
            }
        }
    }
}