package com.mk.farmereats.ui.screens.onboarding

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.utils.AppConstants.onboardingItems
import androidx.compose.runtime.getValue
import com.mk.farmereats.ui.components.DotsIndicator

@Composable
fun OnboardingScreen(
    onLoginClick : () -> Unit ,
    onJoinClick : () -> Unit
) {

    val pagerState =
        rememberPagerState(pageCount = { onboardingItems.size }) // it didn't return a state so not using by

    val currentPage = pagerState.currentPage
    val item = onboardingItems[currentPage]

    val bgColor by animateColorAsState(
        targetValue = item.bgColor,
        animationSpec = tween(
            durationMillis = 2000
        ),
        label = "bgAnimation"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(bgColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .statusBarsPadding()
            ,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.1f))

            Image(
                painter = painterResource(id = item.image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.weight(0.1f))

            Card(
                shape = RoundedCornerShape(30.dp),
                modifier = Modifier
                    .fillMaxWidth(),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp)
                        .navigationBarsPadding()
                    ,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Text(
                        text = item.title,
                        fontSize = 22.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                    HorizontalPager(
                        state = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    ) { page ->


                        Text(
                            text = onboardingItems[page].description,
                            textAlign = TextAlign.Center,
                            color = Color.DarkGray,
                            fontSize = 13.sp
                        )

                    }



                    Spacer(modifier = Modifier.height(10.dp))


                    DotsIndicator(
                        totalDots = onboardingItems.size,
                        selectedIndex = currentPage
                    )


                    Spacer(modifier = Modifier.height(10.dp))


                    Button(
                        colors = ButtonDefaults.buttonColors(
                            containerColor = bgColor,
                            contentColor = Color.White
                        ),
                        onClick = {
                            onJoinClick()
                        },
                        shape = RoundedCornerShape(50),
                    ) {
                        Text(
                            "Join the movement!", fontSize = 16.sp,
                            modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(17.dp))

                    Text(
                        text = "Login",
                        textDecoration = TextDecoration.Underline,
                        color = Color.DarkGray,
                        fontSize = 16.sp,
                        modifier = Modifier.clickable{
                            onLoginClick()
                        }
                    )

                    Spacer(modifier = Modifier.height(10.dp))

                }
            }

        }
    }

}


@Preview(showSystemUi = true , showBackground = true)
@Composable
fun OnboardingScreenPreview(){
    OnboardingScreen(
        {} ,{}
    )
}