package com.capncook.caffix.feature.auth.presentation.login.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun LoginInputField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = Int.MAX_VALUE,
    showCountryCode: Boolean = false
) {


    TextField(
        value = value,
        onValueChange = { newValue ->
            if(newValue.length <= maxLength){
                onValueChange(newValue)
            }
        },
        placeholder = {

            Text(
                text = placeholderText,
                fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Normal,
                fontSize = 16.sp,
                color = Color.Gray
            )
        },

        leadingIcon = {

            if(showCountryCode) {

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                ) {
                    Text(
                        text = "+91",
                        fontFamily = poppinsFontFamily,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 18.sp,
                        color = Color.DarkGray
                    )

                    Spacer(modifier = Modifier.width(12.dp))

                    // Vertical Divider matching the image
                    Box(
                        modifier = Modifier
                            .height(24.dp)
                            .width(1.dp)
                            .background(Color.LightGray)
                    )

                    Spacer(modifier = Modifier.width(4.dp))
                }
            }else null

        },
        shape = RoundedCornerShape(
            12.dp
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            color = Color.DarkGray
        ),
        modifier = modifier
            .height(57.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color(0xFFF8F4ED),
            cursorColor = Color(0xFF6F4E37),
            focusedTextColor = Color(0xFF3E2723),
            unfocusedTextColor =  Color(0xFF3E2723)
        )
    )

}