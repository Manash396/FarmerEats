package com.mk.farmereats.ui.screens.register



import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mk.farmereats.R
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.ui.theme.orangeColor
import com.mk.farmereats.utils.AppConstants.days
import com.mk.farmereats.utils.AppConstants.timeSlots
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StepFourScreen(
    registerState : RegisterRequest,
    goBack : () -> Unit = {},
    onSubmit : () -> Unit = {},
    onValueChange: (RegisterRequest) -> Unit
) {


    var selectedDay by remember { mutableStateOf("M") }
    var selectedSlots by remember { mutableStateOf(setOf<String>()) }

    var businessHoursState by remember { mutableStateOf(RegisterRequest.BusinessHours()) }

    var hourListError by remember { mutableStateOf(false) }



    LaunchedEffect(hourListError) {
        delay(2000)
        hourListError = false
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
                text = "Business Hours",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF2D2D2D)
            )

            Text(
                text = "Choose the hours your farm is open for pickups." +
                        "This will allow customers to order delivers",
                fontSize = 14.sp,
                color = Color.Gray
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ){


            }

//            days
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                days.forEach { day ->
                    val isSelected = day == selectedDay

                    Box(
                        modifier = Modifier
                            .background(
                                if (isSelected) orangeColor else Color.LightGray,
                                RoundedCornerShape(8.dp)
                            )
                            .clickable {
                                selectedDay = day

                                // Load saved slots for that day
                                selectedSlots = getSlotsForDay(day, businessHoursState).toSet()
                            }
                            .padding(horizontal = 12.dp, vertical = 8.dp)
                    ) {
                        Text(day, color = if (isSelected) Color.White else Color.Black)
                    }
                }
            }

//            slots

            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                timeSlots.chunked(2).forEach { rowSlots ->
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        rowSlots.forEach { slot ->
                            val isSelected = selectedSlots.contains(slot)

                            Box(
                                modifier = Modifier
                                    .weight(1f)
                                    .background(
                                        if (isSelected) Color(0xFFE6B566) else Color.LightGray,
                                        RoundedCornerShape(12.dp)
                                    )
                                    .clickable {
                                        selectedSlots = if (isSelected) {
                                            selectedSlots - slot
                                        } else {
                                            selectedSlots + slot
                                        }

                                        // Save to model
                                        businessHoursState =
                                            updateBusinessHours(selectedDay, selectedSlots.toList(), businessHoursState)

                                        onValueChange(
                                            registerState.copy(
                                                businessHours = businessHoursState
                                            )
                                        )
                                    }
                                    .padding(12.dp)
                            ) {
                                Text(slot, fontSize = 12.sp)
                            }
                        }
                    }
                }
            }


            if(hourListError){
                Text("required business hour" , color = orangeColor)
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
                    val hasAnySlot = with(businessHoursState) {
                        mon.isNotEmpty() || tue.isNotEmpty() || wed.isNotEmpty() ||
                                thu.isNotEmpty() || fri.isNotEmpty() || sat.isNotEmpty() || sun.isNotEmpty()
                    }

                    Log.d("MKdev" , businessHoursState.toString())

                    if (!hasAnySlot) {
                        hourListError = true
                    } else {
                        onSubmit()
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
                Text("Submit", fontSize = 16.sp)
            }
        }

    }

}

@Preview(showSystemUi = true , showBackground = true)
@Composable
fun StepFourScreenPreview(){
    StepFourScreen(
        registerState = RegisterRequest() ,
        onValueChange = {}
    )
}

fun getSlotsForDay(day: String, bh: RegisterRequest.BusinessHours): List<String> {
    return when (day) {
        "M" -> bh.mon
        "T" -> bh.tue
        "W" -> bh.wed
        "Th" -> bh.thu
        "F" -> bh.fri
        "S" -> bh.sat
        "Su" -> bh.sun
        else -> emptyList()
    }
}

fun updateBusinessHours(
    day: String,
    slots: List<String>,
    bh: RegisterRequest.BusinessHours
): RegisterRequest.BusinessHours {
    return when (day) {
        "M" -> bh.copy(mon = slots)
        "T" -> bh.copy(tue = slots)
        "W" -> bh.copy(wed = slots)
        "Th" -> bh.copy(thu = slots)
        "F" -> bh.copy(fri = slots)
        "S" -> bh.copy(sat = slots)
        "Su" -> bh.copy(sun = slots)
        else -> bh
    }
}
