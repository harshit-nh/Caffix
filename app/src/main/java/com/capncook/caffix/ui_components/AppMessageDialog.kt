package com.capncook.caffix.ui_components

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.capncook.caffix.common.ui_components.theme.poppinsFontFamily

@Composable
fun AppMessageDialog(
    show: Boolean,
    title: String,
    message: String,
    onDismiss: () -> Unit
){

    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            title = {
                Text(
                    text = title, fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp, textAlign = TextAlign.Center
                )
            },
            text = {
                Text(
                    text = message, fontFamily = poppinsFontFamily, fontWeight = FontWeight.Normal,
                    fontSize = 18.sp
                )
            },
            confirmButton = { TextButton(onClick = onDismiss) { Text(text = "OK", fontFamily = poppinsFontFamily, fontWeight = FontWeight.SemiBold,
                fontSize = 16.sp)}}
        )
    }
}