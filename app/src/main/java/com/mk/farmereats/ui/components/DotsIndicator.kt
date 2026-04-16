package com.mk.farmereats.ui.components

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DotsIndicator(
    totalDots: Int,
    selectedIndex: Int
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center
    ) {

        repeat(totalDots) { index ->

            val isSelected = index == selectedIndex

            val width by animateDpAsState(
                targetValue = if (isSelected) 22.dp else 8.dp,
                label = "dotWidth"
            )

            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .height(8.dp)
                    .width(width)
                    .clip(RoundedCornerShape(50))
                    .background(Color.Black)
            )
        }
    }
}