package com.mk.farmereats.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.R
import com.mk.farmereats.ui.components.SocialButton
import com.mk.farmereats.ui.theme.orangeColor
import com.mk.farmereats.ui.theme.textContainerColor
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    goBackToLogin: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {

    var currentStep by remember { mutableIntStateOf(1) }


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

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Signup $currentStep of 4",
            color = Color.Gray,
            fontSize = 14.sp
        )

        Spacer(modifier = Modifier.height(10.dp))

        when(currentStep){
            1 -> {
                StepOneScreen(goBackToLogin = goBackToLogin , onContinue = { currentStep++ })
            }
            2 -> {

            }
            3 -> {

            }
            4 -> {

            }
        }

        Spacer(modifier = Modifier.height(17.dp))




    }

}

@Preview(showSystemUi = true , showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen({},{})
}


@Composable
fun StepOneScreen(
    goBackToLogin: () -> Unit = {},
    onContinue : () -> Unit = {}
){

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var fullName by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var confirmPass by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var fullNameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var confirmPassError by remember { mutableStateOf(false) }

    LaunchedEffect(emailError , passwordError) {
        delay(2000)
        emailError = false
        passwordError = false
    }

    Column(
        modifier = Modifier.fillMaxWidth()
            .navigationBarsPadding()
            .padding(bottom = 10.dp)
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = "Welcome!",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

//            Spacer(modifier = Modifier.height(20.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                SocialButton(R.drawable.google_icon)
                SocialButton(R.drawable.apple_icon)
                SocialButton(R.drawable.meta_icon)

            }

//            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "or signup with",
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

//            Spacer(modifier = Modifier.height(20.dp))

            Column {

                OutlinedTextField(
                    isError = fullNameError,
                    value = fullName,
                    onValueChange = { fullName = it },
                    placeholder = { Text("Full Name", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.person_icon),
                            contentDescription = null,
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
                    isError = emailError,
                    value = email,
                    onValueChange = { email = it },
                    placeholder = { Text("Email Address", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.email_icon),
                            contentDescription = null,
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
                    isError = phoneError,
                    value = phone,
                    onValueChange = { phone = it },
                    placeholder = { Text("Phone Number", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.phone_icon),
                            contentDescription = null,
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
                            painter = painterResource(R.drawable.password_icon),
                            contentDescription = null,
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
                    isError = confirmPassError,
                    value = confirmPass,
                    onValueChange = { confirmPass = it },
                    placeholder = { Text("Re-enter Password", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.password_icon),
                            contentDescription = null,
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
            }
        }

        Spacer(modifier = Modifier.height(34.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Text(
                text = "Login",
                textDecoration = TextDecoration.Underline,
                color = Color.DarkGray,
                fontSize = 16.sp,
                modifier = Modifier.clickable {
                    goBackToLogin()
                }
            )

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {

                    emailError = email.isEmpty()
                    passwordError = password.isEmpty()
                    val hasError = emailError || passwordError

//                if (!hasError)

                },
                shape = RoundedCornerShape(50),
                colors = ButtonDefaults.buttonColors(
                    containerColor = orangeColor
                ),
                modifier = Modifier
                    .width(200.dp)
                    .height(55.dp)
            ) {
                Text("Continue", fontSize = 16.sp)
            }
        }
    }

}