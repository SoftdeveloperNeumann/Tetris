package com.example.tetris.engin

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class GameViewModel : ViewModel(){

    private val _viewState = mutableStateOf(ViewState())
    val viewState = _viewState
}

data class ViewState(val score:Int = 0){

}