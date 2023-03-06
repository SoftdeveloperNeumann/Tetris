package com.example.tetris.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.clipRect
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tetris.engin.Brick
import com.example.tetris.engin.Spirit
import com.example.tetris.engin.SpiritType
import com.example.tetris.ui.theme.BrickMatrix
import com.example.tetris.ui.theme.BrickSpirit
import com.example.tetris.ui.theme.ScreenBackground
import kotlin.math.min


@Composable
fun GameScreen(modifier: Modifier = Modifier) {

}

@Composable
fun GameScoreboard(
    modifier: Modifier = Modifier,
    brickSize: Float = 35f,
    spirit: Spirit,
    score: Int = 0,
    line: Int = 0,
    level: Int = 1,
    isMute: Boolean = false,
    isPaused: Boolean = false
) {
    Row(modifier = modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.weight(0.65f))
        val textSize = 12.sp
        val margin = 12.dp
        Column(
            Modifier
                .fillMaxHeight()
                .weight(0.35f)
        ) {
            Text(text = "Score", fontSize = textSize)
            LedNumber(Modifier.fillMaxWidth(), digits = 6)
        }
    }


}

private fun DrawScope.drawBricks(brick: List<Brick>, brickSize: Float, matrix: Pair<Int, Int>) {
    clipRect(
        left = 0f,
        top = 0f,
        right = matrix.first * brickSize,
        bottom = matrix.second * brickSize
    ) {
        brick.forEach {
            drawBrick(brickSize, it.location, BrickSpirit)
        }
    }
}

private fun DrawScope.drawBrick(brickSize: Float, offset: Offset, color: Color) {
    val actualLocation = Offset(
        offset.x * brickSize,
        offset.y * brickSize
    )
    val outerSize = brickSize * 0.8f
    val outerOffset = (brickSize - outerSize) / 2

    drawRect(
        color = color,
        topLeft = actualLocation + Offset(outerOffset, outerOffset),
        size = Size(outerSize, outerSize),
        style = Stroke(outerSize / 10)
    )

    val innerSize = brickSize * 0.5f
    val innerOffset = (brickSize - innerSize) / 2

    drawRect(
        color = color,
        topLeft = actualLocation + Offset(innerOffset, innerOffset),
        size = Size(innerSize, innerSize)
    )
}

private fun DrawScope.drawSpirit(spirit: Spirit, brickSize: Float, matrix: Pair<Int, Int>) {
    clipRect(
        0f, 0f,
        matrix.first * brickSize,
        matrix.second * brickSize
    ) {
        spirit.location.forEach {
            drawBrick(
                brickSize,
                Offset(it.x, it.y),
                BrickSpirit
            )
        }
    }
}

private fun DrawScope.drawMatrix(brickSize: Float, matrix: Pair<Int, Int>) {
    (0 until matrix.first).forEach { x ->
        (0 until matrix.second).forEach() { y ->
            drawBrick(
                brickSize,
                Offset(x.toFloat(), y.toFloat()),
                BrickMatrix
            )
        }

    }
}

private fun DrawScope.drawMatrixBorder(brickSize: Float, matrix: Pair<Int, Int>) {
    val gap = matrix.first * brickSize * 0.05f
    drawRect(
        Color.Black,
        size = Size(
            matrix.first * brickSize + gap,
            matrix.second * brickSize + gap
        ),
        topLeft = Offset(-gap / 2, -gap / 2),
        style = Stroke(1.dp.toPx())
    )
}

@Preview
@Composable
fun PreviewGameScreen(
    modifier: Modifier = Modifier
        .height(300.dp)
        .width(260.dp)
) {
    Box(
        modifier = modifier
            .background(Color.Black)
            .padding(1.dp)
            .background(ScreenBackground)
            .padding(10.dp)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .background(ScreenBackground)
        ) {
            val brickSize = min(
                size.width / 12,
                size.height / 24
            )
            drawMatrix(brickSize, 12 to 24)
            drawMatrixBorder(brickSize, 12 to 24)
        }
        val type = SpiritType[6]
        GameScoreboard(spirit = Spirit(type, Offset(0f, 0f)).rotate(),
        score = 1205,
            line = 12
        )

    }
}


@Preview
@Composable
fun PreviewSpiritTypes() {
    Row(
        Modifier
            .size(300.dp, 50.dp)
            .background(ScreenBackground)
    ) {
        val matrix = 2 to 4
        SpiritType.forEach {
            Canvas(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .padding(5.dp)
            ) {
                drawBricks(
                    Brick.of(
                        Spirit(it).adjustOffset(matrix)
                    ),
                    min(
                        size.width / matrix.first,
                        size.height / matrix.second
                    ),
                    matrix
                )
            }
        }
    }
}