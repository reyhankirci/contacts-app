package com.example.contacts.ui.components

import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.AnchoredDraggableState
import androidx.compose.foundation.gestures.DraggableAnchors
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import kotlin.math.roundToInt

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MAnchoredDraggableBox(
    modifier: Modifier,
    firstContent: @Composable (modifier: Modifier) -> Unit,
    secondContent: @Composable (modifier: Modifier) -> Unit,
    secondContentWidthSize: Dp
) {
    val density = LocalDensity.current
    val positionalThresholds: (totalDistance: Float) -> Float =
        { totalDistance -> totalDistance * 0.5f }
    val velocityThreshold: () -> Float = { with(density) { 100.dp.toPx() } }

    val state = remember {
        AnchoredDraggableState(
            initialValue = DragAnchors.Start,
            positionalThresholds,
            velocityThreshold,
            animationSpec = tween()
        ).apply {
            val newAnchors = with(density) {
                DraggableAnchors {
                    DragAnchors.Start at 0.dp.toPx()
                    DragAnchors.End at -secondContentWidthSize.toPx()
                }
            }
            updateAnchors(newAnchors)
        }
    }
    Box(
        modifier = modifier
    ) {
        firstContent(
            Modifier
                .fillMaxWidth()
                .offset {
                    IntOffset(
                        state
                            .requireOffset()
                            .roundToInt(), 0
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal)
        )
        secondContent(
            Modifier
                .align(Alignment.CenterEnd)
                .offset {
                    IntOffset(
                        (state.requireOffset() + secondContentWidthSize.toPx()).roundToInt(), 0
                    )
                }
                .anchoredDraggable(state, Orientation.Horizontal)
        )
    }

}

enum class DragAnchors {
    Start,
    End,
}