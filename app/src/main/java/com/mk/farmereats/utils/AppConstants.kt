package com.mk.farmereats.utils

import androidx.compose.ui.graphics.Color
import com.mk.farmereats.R
import com.mk.farmereats.domain.model.OnboardingItem

object AppConstants {

    val onboardingItems = listOf(
        OnboardingItem(
            image = R.drawable.ic_farm,
            title = "Quality",
            description = "Sell your farm fresh products directly to consumers, cutting" +
                    "out the middle man and reducing emissions of the global supply.",
            bgColor = Color(0xFF6FBF73)
        ),
        OnboardingItem(
            image = R.drawable.ic_delivery,
            title = "Convenient",
            description = "Our team of delivery drivers will make sure your orders are picked up on time " +
                    "promptly delivered to your customers.",
            bgColor = Color(0xFFE07A5F)
        ),
        OnboardingItem(
            image = R.drawable.ic_local,
            title = "Local",
            description = "We love the earth and know you do too! Join us in reducing our local chain" +
                    "carbon footprint one order at a time",
            bgColor = Color(0xFFF2CC8F)
        )
    )

    val IndianStates =  listOf(
        "Assam","Arunachal Pradesh","Bihar","Chhattisgarh","Goa","Gujarat",
        "Haryana","Himachal Pradesh","Jharkhand","Karnataka","Kerala",
        "Madhya Pradesh","Maharashtra","Manipur","Meghalaya","Mizoram",
        "Nagaland","Odisha","Punjab","Rajasthan","Sikkim","Tamil Nadu",
        "Telangana","Tripura","Uttar Pradesh","Uttarakhand","West Bengal"
    )

    val days = listOf("M", "T", "W", "Th", "F", "S", "Su")

    val timeSlots = listOf(
        "8:00am - 10:00am",
        "10:00am - 1:00pm",
        "1:00pm - 4:00pm",
        "4:00pm - 7:00pm",
        "7:00pm - 10:00pm"
    )
}