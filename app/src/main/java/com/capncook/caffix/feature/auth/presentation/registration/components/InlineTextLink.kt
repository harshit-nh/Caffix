package com.capncook.caffix.feature.auth.presentation.registration.components

import androidx.compose.foundation.text.BasicText
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview


@Composable
fun InlineTextLink(
    normalText: String,
    linkText: String,
    onLinkClick: () -> Unit,
    modifier: Modifier = Modifier,
    style: TextStyle = TextStyle.Default,
    normalTextSpanStyle: SpanStyle = SpanStyle(color = Color.Gray),
    linkTextSpanStyle: SpanStyle = SpanStyle(
        color = Color(0xFF5C4033),
        textDecoration = TextDecoration.Underline
    )
) {

    val annotatedText = buildAnnotatedString {
        withStyle(style = normalTextSpanStyle) {
            append(normalText)
        }

        pushStringAnnotation(tag = "LINK_ACTION", annotation = "clicked")

        withStyle(style = linkTextSpanStyle) {
            append(linkText)
        }
        pop()
    }

    ClickableText (
        text = annotatedText,
        modifier = modifier,
        style = style,
        onClick = { offset ->
            annotatedText.getStringAnnotations(
                tag = "LINK_ACTION",
                start = offset,
                end = offset
            ).firstOrNull()?.let {
                onLinkClick()
            }
        }
    )


}