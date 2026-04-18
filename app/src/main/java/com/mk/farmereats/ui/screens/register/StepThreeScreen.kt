package com.mk.farmereats.ui.screens.register

import android.content.Context
import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.platform.LocalContext
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
fun StepThreeScreen(
    registerState : RegisterRequest,
    goBack : () -> Unit = {},
    onContinue : () -> Unit = {},
    onValueChange: (RegisterRequest) -> Unit
) {

    val context  = LocalContext.current

    var attachedError by remember { mutableStateOf(false) }

    var selectedPdfUri by remember { mutableStateOf<Uri?>(registerState.registrationProof) }
    var selectedFileName by remember { mutableStateOf("") }

    val pdfPicker  = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            selectedPdfUri =  uri

            onValueChange(registerState.copy(registrationProof = uri))

            selectedFileName = getFileName(context , it)
        }
    }

    LaunchedEffect(selectedPdfUri) {
        selectedPdfUri?.let { selectedFileName = getFileName(context , it) }
    }

    LaunchedEffect(attachedError) {
        delay(2000)
        attachedError = false
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
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {

            Text(
                text = "Verification",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Text(
                text = "Attached proof of Department of Agriculture registrations" +
                        " i.e. Florida Fresh, USDA Approved, USDA Organic",
                fontSize = 14.sp,
                color = Color.Gray
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){

                Text(
                    text = "Attach proof of registration",
                    fontSize = 14.sp,
                    color = Color.DarkGray
                )


                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(color = orangeColor, shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.camera_icon),
                        contentDescription = null,
                        tint = Color.White,
                        modifier = Modifier.size(20.dp)
                            .clickable {
                                pdfPicker.launch("application/pdf")
                            }
                    )
                }

            }

//            id proof container

            selectedPdfUri?.let {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(textContainerColor, RoundedCornerShape(12.dp))
                        .padding(12.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {


                    Text(
                        text = selectedFileName,
                        fontSize = 12.sp,
                        color = Color.DarkGray,
                        modifier = Modifier.weight(1f)
                    )

                    Icon(
                        painter = painterResource(R.drawable.cross_icon),
                        contentDescription = null,
                        tint = Color.Black,
                        modifier = Modifier.size(20.dp)
                            .clickable {
                            selectedPdfUri = null
                            selectedFileName = ""
                            onValueChange(registerState.copy(registrationProof = null))
                        }
                    )

                }
            }

            if(attachedError){
                Text("required a proof of registration" , color = orangeColor)
            }


        }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp),
                verticalAlignment = Alignment.CenterVertically,
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
                        if(selectedPdfUri == null){
                            attachedError = true
                        }else{
                            onContinue()
                        }

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

@Preview(showSystemUi = true , showBackground = true)
@Composable
fun StepThreeScreenPreview(){
    StepThreeScreen(
        registerState = RegisterRequest() ,
        onValueChange = {}
    )
}

fun getFileName(context: Context, uri: Uri): String {
    var name = "file.pdf"

    val cursor = context.contentResolver.query(uri, null, null, null, null)
    cursor?.use {
        val nameIndex = it.getColumnIndex(OpenableColumns.DISPLAY_NAME)
        if (it.moveToFirst() && nameIndex != -1) {
            name = it.getString(nameIndex)
        }
    }

    return name
}