package com.capncook.caffix.feature.auth.presentation.registration.components


import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily


@Composable
fun UserInputText(
    value: String,
    onValueChange: (String) -> Unit,
    placeholderText: String,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    maxLength: Int = Int.MAX_VALUE,
    icon: @Composable (() -> Unit)? = null
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
        leadingIcon = icon,
        shape = RoundedCornerShape(
            topStart = 12.dp,
            topEnd = 12.dp,
            bottomEnd = 12.dp,
            bottomStart = 12.dp
        ),
        singleLine = true,
        keyboardOptions = keyboardOptions,
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