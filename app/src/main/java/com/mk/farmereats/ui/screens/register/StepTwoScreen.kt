package com.mk.farmereats.ui.screens.register

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuAnchorType
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.R
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.ui.components.SocialButton
import com.mk.farmereats.ui.theme.orangeColor
import com.mk.farmereats.ui.theme.textContainerColor
import com.mk.farmereats.utils.AppConstants.IndianStates
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepTwoScreen(
    registerState : RegisterRequest,
    goBack : () -> Unit = {},
    onContinue : () -> Unit = {},
    onValueChange: (RegisterRequest) -> Unit
){
    
    var businessError by remember { mutableStateOf(false) }
    var informalNameError by remember { mutableStateOf(false) }
    var streetAddressError by remember { mutableStateOf(false) }
    var cityError by remember { mutableStateOf(false) }
    var stateError by remember { mutableStateOf(false) }
    var zipCodeError by remember { mutableStateOf(false) }

    var hasEmptyField by remember { mutableStateOf(false) }
    var expandedStateList by remember { mutableStateOf(false) }

    LaunchedEffect(hasEmptyField) {
        delay(2000)
        businessError = false
        informalNameError = false
        streetAddressError = false
        cityError = false
        stateError = false
        zipCodeError = false
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
                .weight(1f)
            ,
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            Text(
                text = "Farm Info",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Column {

                OutlinedTextField(
                    isError = businessError,
                    value = registerState.businessName,
                    onValueChange = {
                        onValueChange(registerState.copy(businessName = it))
                    },
                    placeholder = { Text("Business Name", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.business_icon),
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
                    isError = informalNameError,
                    value = registerState.informalName,
                    onValueChange = {
                        onValueChange(registerState.copy(informalName = it))
                    },
                    placeholder = { Text("Informal Name", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.informal_icon),
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
                    isError = streetAddressError,
                    value = registerState.address,
                    onValueChange = {
                        onValueChange(registerState.copy(address = it))
                    },
                    placeholder = { Text("Street Address", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.adress_icon),
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
                    isError = cityError,
                    value = registerState.city,
                    onValueChange = {
                        onValueChange(registerState.copy(city = it))
                    },
                    placeholder = { Text("City", color = Color.Gray) },
                    leadingIcon = {
                        Icon(
                            painter = painterResource(R.drawable.city_icon),
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

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    ExposedDropdownMenuBox(
                        expanded = expandedStateList,
                        onExpandedChange = { expandedStateList = !expandedStateList },
                        modifier = Modifier.weight(1f)
                    ) {

                        OutlinedTextField(
                            textStyle = TextStyle(
                                fontSize = 11.sp,
                                fontStyle = FontStyle.Italic
                            ),
                            value = registerState.state,
                            onValueChange = {},
                            readOnly = true,
                            placeholder = { Text("State", color = Color.Gray) },
                            trailingIcon = {
                                ExposedDropdownMenuDefaults.TrailingIcon(
                                    expanded = expandedStateList
                                )
                            },
                            shape = RoundedCornerShape(12.dp),
                            modifier = Modifier
                                .menuAnchor(
                                    type = ExposedDropdownMenuAnchorType.PrimaryNotEditable,
                                    true
                                )
                                .fillMaxWidth(),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedContainerColor = textContainerColor,
                                unfocusedContainerColor = textContainerColor,
                                disabledContainerColor = textContainerColor,
                                focusedBorderColor = Color.Transparent,
                                unfocusedBorderColor = Color.Transparent,
                                disabledBorderColor = Color.Transparent,
                                focusedTextColor = orangeColor,
                                unfocusedTextColor = Color.Gray,
                                disabledTextColor = Color.Gray
                            )
                        )

                        DropdownMenu(
                            expanded = expandedStateList,
                            onDismissRequest = { expandedStateList = false }
                        ) {
                            IndianStates.forEach { state ->
                                DropdownMenuItem(
                                    text = { Text(state) },
                                    onClick = {
                                        onValueChange(registerState.copy(state = state))
                                        expandedStateList = false
                                    }
                                )
                            }
                        }

                    }

                    OutlinedTextField(
                        isError = zipCodeError,
                        value = registerState.zipCode,
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        onValueChange = {newVal ->
                            if (newVal.length<=6) onValueChange(registerState.copy(zipCode = newVal))
                        },
                        placeholder = { Text("ZipCode", color = Color.Gray, fontSize = 13.sp) },
                        shape = RoundedCornerShape(12.dp),
                        modifier = Modifier.weight(.7f),
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
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    painter = painterResource(R.drawable.back_arrow_icon),
                    contentDescription = null,
                    modifier = Modifier.size(24.dp)
                        .clickable {
                            goBack()
                        }
                )

                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {

                        with(registerState) {
                            businessError = businessName.isEmpty()
                            informalNameError = informalName.isEmpty()
                            streetAddressError = address.isEmpty()
                            cityError = city.isEmpty()
                            zipCodeError = zipCode.isEmpty()
                            stateError = state.isEmpty()
                        }

                        val hasError = businessError || informalNameError || streetAddressError
                                || cityError || zipCodeError || stateError
                        hasEmptyField = hasError
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

}

@Preview(showSystemUi = true , showBackground = true)
@Composable
fun StepTwoScreenPreview(){
    StepTwoScreen(
        registerState = RegisterRequest() ,
        onValueChange = {}
    )
}