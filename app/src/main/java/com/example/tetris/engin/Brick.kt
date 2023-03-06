package com.example.tetris.engin

import androidx.compose.ui.geometry.Offset

data class Brick(val location: Offset = Offset(0,0)) {

    companion object{
        fun of(offsetList: List<Offset>) = offsetList.map { Brick(it) }
    }
}