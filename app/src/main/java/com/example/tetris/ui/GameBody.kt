package com.example.tetris.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.tetris.R
import com.example.tetris.engin.Direction
import com.example.tetris.ui.theme.BodyColor
import com.example.tetris.ui.theme.ScreenBackground
import com.example.tetris.ui.theme.TetrisTheme


val SettingButtonSize = 16.dp
val DirectionButtonSize = 64.dp
val RotateButtonSize = 96.dp


@Composable
fun GameBody(
    clickabel: Clickable = combinedClickable(),
    screen: @Composable () -> Unit
) {
    Column(
        Modifier
            .fillMaxSize()
            .background(Color.Black)
            .background(BodyColor, RoundedCornerShape(15.dp))
            .padding(top = 20.dp)
    ) {
        // Screen
        Box(modifier = Modifier.align(Alignment.CenterHorizontally)) {

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(330.dp, 400.dp)
                    .padding(top = 20.dp)
                    .background(Color.Black.copy(alpha = 0.8f))
                    .padding(5.dp)
                    .background(BodyColor)
            )
            Box(
                modifier = Modifier
                    .width(120.dp)
                    .height(45.dp)
                    .align(Alignment.TopCenter)
                    .background(BodyColor)
            ) {
                Text(
                    text = stringResource(id = R.string.body_label),
                    modifier = Modifier.align(Alignment.Center),
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Cursive,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp
                )
            }

            Box(
                modifier = Modifier
                    .align(Alignment.Center)
                    .size(360.dp, 380.dp)
                    .padding(start = 50.dp, end = 50.dp, top = 50.dp, bottom = 30.dp)
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawScreenBorder(
                        Offset(0f, 0f),
                        Offset(size.width, 0f),
                        Offset(0f, size.height),
                        Offset(size.width, size.height)
                    )
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(6.dp)
                        .background(ScreenBackground)
                ) {
                    screen()
                }

            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        val SettingText = @Composable { text: String, modifier: Modifier ->
            Text(
                text = text,
                modifier = modifier,
                fontSize = 12.sp,
                color = Color.Red,
                textAlign = TextAlign.Center
            )
        }

        // Buttons Settings

        Column(Modifier.padding(start = 40.dp, end = 40.dp)) {
            Row {
                SettingText(stringResource(id = R.string.button_sounds), Modifier.weight(1f))
                SettingText(stringResource(id = R.string.button_pause), Modifier.weight(1f))
                SettingText(stringResource(id = R.string.button_reset), Modifier.weight(1f))
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                //Sound
                GameButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 20.dp),
                    onClick = { clickabel.onMute },
                    size = SettingButtonSize,
                ) {}

                // Pause
                GameButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 20.dp),
                    onClick = { clickabel.onPause },
                    size = SettingButtonSize,
                ) {}

                //Reset
                GameButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 20.dp, end = 20.dp),
                    onClick = { clickabel.onRestart },
                    size = SettingButtonSize,
                ) {}
            }
        }

        Spacer(modifier = Modifier.height(36.dp))

        // Buttons Spielsteuerung

        val ButtonText = @Composable { modifier: Modifier, text: String ->
            Text(
                text = text,
                modifier = modifier,
                color = Color.White.copy(0.9f),
                fontSize = 18.sp
            )
        }
        Row(
            modifier = Modifier
                .padding(start = 40.dp, end = 40.dp)
                .height(160.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .weight(1f)
            ) {
                GameButton(
                    size = DirectionButtonSize,
                    modifier = Modifier.align(Alignment.TopCenter),
                    onClick = { clickabel.onMove(Direction.Up) },
                    autoInvokeWhenPressed = false,
                ){
                    ButtonText(it, stringResource(id = R.string.button_up))
                }
                GameButton(
                    size = DirectionButtonSize,
                    modifier = Modifier.align(Alignment.CenterStart),
                    onClick = { clickabel.onMove(Direction.Left) },
                    autoInvokeWhenPressed = true,
                ){
                    ButtonText(it, stringResource(id = R.string.button_left))
                }
                GameButton(
                    size = DirectionButtonSize,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = { clickabel.onMove(Direction.Right) },
                    autoInvokeWhenPressed = true,
                ){
                    ButtonText(it, stringResource(id = R.string.button_right))
                }
                GameButton(
                    size = DirectionButtonSize,
                    modifier = Modifier.align(Alignment.BottomCenter),
                    onClick = { clickabel.onMove(Direction.Down) },
                    autoInvokeWhenPressed = true,
                ){
                    ButtonText(it, stringResource(id = R.string.button_down))
                }
            }
            Box(modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
            ){
                GameButton(
                    size = RotateButtonSize,
                    modifier = Modifier.align(Alignment.CenterEnd),
                    onClick = {clickabel.onRotate},
                    autoInvokeWhenPressed = false
                ){
                    ButtonText(it, stringResource(id = R.string.button_rotate))
                }
            }
        }
    }

}

fun DrawScope.drawScreenBorder(
    topLef: Offset,
    topRight: Offset,
    bottomLeft: Offset,
    bottomRight: Offset
) {
    var path = Path().apply {
        moveTo(topLef.x, topLef.y)
        lineTo(topRight.x, topRight.y)
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            topLef.y + topRight.x / 2 + topLef.x / 2
        )
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            bottomLeft.y - topRight.x / 2 + topLef.x / 2
        )
        lineTo(bottomLeft.x, bottomLeft.y)
        close()
    }
    drawPath(path, Color.Black.copy(0.5f))

    path = Path().apply {
        moveTo(bottomRight.x, bottomRight.y)
        lineTo(bottomLeft.x, bottomLeft.y)
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            bottomLeft.y - topRight.x / 2 + topLef.x / 2
        )
        lineTo(
            topRight.x / 2 + topLef.x / 2,
            topLef.y + topRight.x / 2 + topLef.x / 2
        )
        lineTo(topRight.x, topRight.y)
        close()
    }

    drawPath(path, Color.White.copy(0.5f))

}

data class Clickable(
    val onMove: (Direction) -> Unit,
    val onRotate: () -> Unit,
    val onRestart: () -> Unit,
    val onPause: () -> Unit,
    val onMute: () -> Unit
)

fun combinedClickable(
    onMove: (Direction) -> Unit = {},
    onRotate: () -> Unit = {},
    onRestart: () -> Unit = {},
    onPause: () -> Unit = {},
    onMute: () -> Unit = {}
) = Clickable(onMove, onRotate, onRestart, onPause, onMute)

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TetrisTheme {
        GameBody() {

        }
    }
}