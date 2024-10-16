package com.aymendev.animations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview

import com.aymendev.animations.ui.theme.AnimationsTheme
import com.aymendev.animations.ui.theme.Orange
import kotlin.math.cos
import kotlin.math.sin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AnimationsTheme {
                val infiniteTransition = rememberInfiniteTransition(label = "infinite")
                val times = infiniteTransition.animateFloat(
                    initialValue = 4f,
                    targetValue = 25f,
                    animationSpec =
                    infiniteRepeatable(
                        animation = tween(5000, easing = LinearEasing),
                        repeatMode = RepeatMode.Reverse
                    ), label = ""
                )
                CircularAnimation(
                    numberOfPoints = 250,
                    times = times.value
                )
            }
        }
    }

    @Composable
    fun CircularAnimation(numberOfPoints: Int, times: Float) {
        val points = mutableListOf<Offset>()

        Canvas(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxSize()
        ) {
            val radius = size.width * 0.4f
            // Center of the canvas
            val centerX = size.width / 2
            val centerY = size.height / 2
            // Draw the point

            // Loop to draw points in a circular pattern
            for (i in 0 until numberOfPoints) {
                // Calculate the angle in radians for each point
                val angle = (2 * Math.PI / numberOfPoints) * i
                // Calculate the x and y coordinates using trigonometry
                val x = centerX + radius * cos(angle).toFloat()
                val y = centerY + radius * sin(angle).toFloat()
                points.add(Offset(x, y))

            }

            drawCircle(
                color = Orange,
                radius = radius,
                style = Stroke(width = 8f)
            )
            // Loop to draw lines connecting each point with its mirror (diametrically opposite)
            for (i in 0 until numberOfPoints - 2) {
                val point = points[i]
                val mirrorIndex = (times * i) % numberOfPoints
                val point2 = points[mirrorIndex.toInt()]
                drawLine(
                    color = Orange,
                    start = point,
                    end = point2,
                    strokeWidth = 4f,
                    cap = StrokeCap.Round
                )
            }
            drawLine(
                color = Orange,
                start = points[numberOfPoints - 1],
                end = center,
                strokeWidth = 4f,
                cap = StrokeCap.Round
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun CircularAnimationPreview() {
        val infiniteTransition = rememberInfiniteTransition(label = "infinite")
        val times = infiniteTransition.animateFloat(
            initialValue = 4f,
            targetValue = 25f,
            animationSpec =
            infiniteRepeatable(
                animation = tween(5000, easing = LinearEasing),
                repeatMode = RepeatMode.Reverse
            ), label = ""
        )
        CircularAnimation(
            numberOfPoints = 250,
            times = times.value
        )

    }

}




