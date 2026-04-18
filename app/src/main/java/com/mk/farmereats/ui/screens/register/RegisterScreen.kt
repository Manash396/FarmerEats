package com.mk.farmereats.ui.screens.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mk.farmereats.R
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.ui.components.SocialButton
import com.mk.farmereats.ui.theme.orangeColor
import com.mk.farmereats.ui.theme.textContainerColor
import kotlinx.coroutines.delay

@Composable
fun RegisterScreen(
    viewModel: RegisterViewModel = hiltViewModel(),
    goBackToLogin: () -> Unit = {},
    onSubmit: () -> Unit = {}
) {

    var currentStep by remember { mutableIntStateOf(1) }

    val context  = LocalContext.current

//    var  registerObjState by remember { mutableStateOf(RegisterRequest() ) }

    val uiState  = viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(uiState.value.isSuccess) {
        if (uiState.value.isSuccess){
            onSubmit()
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {

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

            when (currentStep) {
                1 -> {
                    StepOneScreen(
                        registerState = uiState.value.form,
                        goBackToLogin = goBackToLogin,
                        onContinue = { currentStep++ },
                        onValueChange = { viewModel.updateForm(it) }
                    )
                }

                2 -> {
                    StepTwoScreen(
                        registerState = uiState.value.form,
                        goBack = { currentStep-- },
                        onContinue = { currentStep++ },
                        onValueChange = { viewModel.updateForm(it) }
                    )
                }

                3 -> {
                    StepThreeScreen(
                        registerState = uiState.value.form,
                        goBack = { currentStep-- },
                        onContinue = { currentStep++ },
                        onValueChange = { viewModel.updateForm(it) }
                    )
                }

                4 -> {
                    StepFourScreen(
                        registerState = uiState.value.form,
                        goBack = { currentStep-- },
                        onSubmit = {
                            viewModel.register(context,uiState.value.form)
                        },
                        onValueChange = { viewModel.updateForm(it) }
                    )
                }
            }

            Spacer(modifier = Modifier.height(17.dp))

        }

//        progress Loading
        if (uiState.value.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Black.copy(alpha = 0.3f)),
                contentAlignment = Alignment.Center
            ) {
                androidx.compose.material3.CircularProgressIndicator(
                    color = orangeColor
                )
            }
        }

//        error message dialog
        if (uiState.value.error != null) {
            AlertDialog(
                onDismissRequest = { viewModel.clearError() },
                confirmButton = {
                    TextButton(
                        onClick = { viewModel.clearError() }
                    ) {
                        Text("OK")
                    }
                },
                title = {
                    Text("Error")
                },
                text = {
                    Text(uiState.value.error!!)
                }
            )
        }

    }

}

@Preview(showSystemUi = true , showBackground = true)
@Composable
fun RegisterScreenPreview(){
    RegisterScreen(viewModel() , { },{})
}


@Composable
fun StepOneScreen(
    registerState : RegisterRequest,
    goBackToLogin: () -> Unit = {},
    onContinue : () -> Unit = {},
    onValueChange: (RegisterRequest) -> Unit
){


    var confirmPass by remember { mutableStateOf("") }

    var emailError by remember { mutableStateOf(false) }
    var passwordError by remember { mutableStateOf(false) }
    var fullNameError by remember { mutableStateOf(false) }
    var phoneError by remember { mutableStateOf(false) }
    var confirmPassError by remember { mutableStateOf(false) }

    var hasEmptyField by remember { mutableStateOf(false) }

    LaunchedEffect(hasEmptyField) {
        delay(2000)
        emailError = false
        passwordError = false
        fullNameError = false
        phoneError = false
        confirmPassError = false
        hasEmptyField = false
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
                    value = registerState.fullName,
                    singleLine = true,
                    onValueChange = {
                        onValueChange(registerState.copy(fullName = it))
                                    },
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
                    value = registerState.email,
                    singleLine = true,
                    onValueChange = {
                        onValueChange(registerState.copy(email = it))
                                    },
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
                    value = registerState.phone,
                    singleLine = true,
                    onValueChange = {
                        onValueChange( registerState.copy(phone = it) )
                                    },
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
                    value = registerState.password,
                    singleLine = true,
                    onValueChange = {
                        onValueChange(registerState.copy(password = it))
                                    },
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
                    singleLine = true,
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

                    with(registerState){
                        emailError = email.isEmpty()
                        passwordError = password.isEmpty()
                        fullNameError = fullName.isEmpty()
                        phoneError =  phone.isEmpty()
                    }
                    confirmPassError =  confirmPass.isEmpty()

                    val hasError = emailError || passwordError || confirmPassError
                            || fullNameError || phoneError

                if (!hasError) onContinue()
                    else hasEmptyField = true

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