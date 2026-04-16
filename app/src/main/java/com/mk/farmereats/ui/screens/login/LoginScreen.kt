package com.mk.farmereats.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.R
import com.mk.farmereats.ui.components.SocialButton
import com.mk.farmereats.ui.theme.orangeColor
import com.mk.farmereats.ui.theme.textContainerColor
import kotlinx.coroutines.delay

@Composable
fun LoginScreen(
    onLoginClick: () -> Unit = {},
    onCreateAccountClick: () -> Unit = {},
    onForgotClick: () -> Unit = {}
) {

        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        var emailError by remember { mutableStateOf(false) }
        var passwordError by remember { mutableStateOf(false) }

        LaunchedEffect(emailError , passwordError) {
            delay(2000)
            emailError = false
            passwordError = false
        }


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F5F5))
                .padding(horizontal = 24.dp)
                .statusBarsPadding(),
        ) {
            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "FarmerEats",
                fontSize = 18.sp,
                color = Color.Black
            )

            Spacer(modifier = Modifier.height(40.dp))


            Text(
                text = "Welcome back!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Row {
                Text(
                    text = "New here? ",
                    color = Color.Gray,
                    fontSize = 14.sp
                )
                Text(
                    text = "Create account",
                    color = orangeColor,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.clickable { onCreateAccountClick() }
                )
            }

            Spacer(modifier = Modifier.height(40.dp))

            OutlinedTextField(
                isError = emailError,
                value = email,
                onValueChange = { email = it },
                placeholder = { Text("Email Address", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.email_icon), contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = textContainerColor,
                    unfocusedContainerColor = textContainerColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = orangeColor,
                    focusedTextColor = orangeColor,
                    unfocusedTextColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                isError = passwordError,
                value = password,
                onValueChange = { password = it },
                placeholder = { Text("Password", color = Color.Gray) },
                leadingIcon = {
                    Icon(
                        painter = painterResource(R.drawable.password_icon), contentDescription = null,
                        modifier = Modifier.size(20.dp)
                    )
                },
                trailingIcon = {
                    Text(
                        text = "Forget?  ",
                        color = orangeColor,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.clickable { onForgotClick() }
                    )
                },
                shape = RoundedCornerShape(12.dp),
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = textContainerColor,
                    unfocusedContainerColor = textContainerColor,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent,
                    cursorColor = orangeColor,
                    focusedTextColor = orangeColor,
                    unfocusedTextColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(34.dp))

            Button(
                onClick = {

                    emailError = email.isEmpty()
                    passwordError = password.isEmpty()

                    val hasError = emailError || passwordError

                    if (!hasError) onLoginClick()

                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp)
            ) {
                Text("Login", fontSize = 16.sp)
            }

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "or login with",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                SocialButton(R.drawable.google_icon) // replace with Google icon
                SocialButton(R.drawable.apple_icon) // Apple
                SocialButton(R.drawable.meta_icon) // Facebook

            }

        }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun LoginScreenPreview() {
    LoginScreen()
}