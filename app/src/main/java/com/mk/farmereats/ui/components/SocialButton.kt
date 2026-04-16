package com.mk.farmereats.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SocialButton(iconId: Int) {
    Box(
        modifier = Modifier
            .width(90.dp)
            .height(50.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .clickable {}
            .border(1.dp, Color.LightGray, RoundedCornerShape(20.dp)),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(iconId),
            contentDescription = null,
            modifier = Modifier.size(30.dp)
        )
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun SocialButtonPreview(){
    SocialButton(
        R.drawable.google_icon
    )
}