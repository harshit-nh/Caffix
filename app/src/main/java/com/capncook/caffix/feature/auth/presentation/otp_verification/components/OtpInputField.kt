package com.capncook.caffix.feature.auth.presentation.otp_verification.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun OtpInputField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = Int.MAX_VALUE
) {


    TextField(
        value = value,
        onValueChange = { newValue ->
            if(newValue.length <= maxLength){
                onValueChange(newValue)
            }
        },
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomEnd = 12.dp,
            bottomStart = 12.dp
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
        textStyle = TextStyle(
            fontSize = 22.sp,
            fontFamily = poppinsFontFamily,
            fontWeight = FontWeight.SemiBold,
            textAlign = TextAlign.Center,
            color = Color(0xFF3E2723)
        ),
        modifier = modifier
            .height(60.dp)
            .width(52.dp),
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