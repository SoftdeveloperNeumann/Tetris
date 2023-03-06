package com.example.tetris.engin

import androidx.compose.ui.geometry.Offset
import kotlin.math.absoluteValue


data class Spirit(
    val shape: List<Offset> = emptyList(),
    val offset: Offset = Offset(0,0)
) {
    val location: List<Offset> = shape.map { it + offset }

    fun moveBy(step: Pair<Int, Int>): Spirit =
        copy(offset = offset + Offset(step.first, step.second))

    fun rotate(): Spirit {
        val newShape = shape.toMutableList()
        for (i in shape.indices) {
            newShape[i] = Offset(shape[i].y, -shape[i].x)
        }
        return copy(shape = newShape)
    }

    fun adjustOffset(matrix: Pair<Int, Int>, adjustY: Boolean = true): Spirit {
        val yOffset =
            if (adjustY)
                (location.minByOrNull { it.y }?.y?.takeIf { it < 0 }?.absoluteValue ?: 0).toInt() +
                        (location.maxByOrNull { it.y }?.y?.takeIf { it > matrix.second - 1 }
                            ?.let { matrix.second - it - 1 } ?: 0).toInt()
            else 0
        val xOffset =
            (location.minByOrNull { it.x }?.x?.takeIf { it < 0 }?.absoluteValue ?: 0).toInt() +
                    (location.maxByOrNull { it.x }?.x?.takeIf { it > matrix.first - 1 }
                        ?.let { matrix.first - it - 1 } ?: 0).toInt()
        return moveBy(xOffset to yOffset)
    }

    companion object {
        val Empty = Spirit()
    }
}