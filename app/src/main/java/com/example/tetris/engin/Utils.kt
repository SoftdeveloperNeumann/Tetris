package com.example.tetris.engin



enum class Direction{
    Left, Up, Right,Down
}

fun Offset(x:Int, y:Int) = androidx.compose.ui.geometry.Offset(x.toFloat(),y.toFloat())