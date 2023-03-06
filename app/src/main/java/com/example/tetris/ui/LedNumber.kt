package com.example.tetris.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tetris.engin.LedFontFamily
import com.example.tetris.ui.theme.BrickMatrix
import com.example.tetris.ui.theme.BrickSpirit

@Composable
fun LedNumber(
    modifier: Modifier = Modifier,
    num: Int = 0,
    digits: Int,
    fillZero: Boolean = false
) {
    val textSite = 16.sp
    val textWidth = 8.dp
    Box(modifier = modifier) {
        Row(Modifier.align(Alignment.CenterEnd)) {
            repeat(digits) {
                Text(
                    text = "8",
                    fontSize = textSite,
                    color = BrickMatrix,
                    fontFamily = LedFontFamily,
                    modifier = Modifier.width(textWidth),
                    textAlign = TextAlign.End
                )
            }
        }
        Row(Modifier.align(Alignment.CenterEnd)) {
            val str = if(fillZero) String.format("%0${digits}d",num) else num.toString()
            str.iterator().forEach {
                Text(text = it.toString(),
                    fontSize = textSite,
                    color = BrickSpirit,
                    fontFamily = LedFontFamily,
                    modifier = Modifier.width(textWidth),
                    textAlign = TextAlign.End

                    )
            }
        }
    }
}