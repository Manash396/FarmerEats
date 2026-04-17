package com.mk.farmereats.data.remote.dto

import android.net.Uri
import android.os.Parcelable
import com.mk.farmereats.domain.model.RegisterRequest

@kotlinx.parcelize.Parcelize
data class RegisterRequestDto(

    @com.google.gson.annotations.SerializedName("full_name")
    val fullName: String = "",

    @com.google.gson.annotations.SerializedName("email")
    val email: String  = "",

    @com.google.gson.annotations.SerializedName("phone")
    val phone: String  = "",

    @com.google.gson.annotations.SerializedName("password")
    val password: String  = "",

    @com.google.gson.annotations.SerializedName("role")
    val role: String  = "",

    @com.google.gson.annotations.SerializedName("business_name")
    val businessName: String  = "",

    @com.google.gson.annotations.SerializedName("informal_name")
    val informalName: String  = "",

    @com.google.gson.annotations.SerializedName("address")
    val address: String  = "",

    @com.google.gson.annotations.SerializedName("city")
    val city: String  = "",

    @com.google.gson.annotations.SerializedName("state")
    val state: String  = "",

    @com.google.gson.annotations.SerializedName("zip_code")
    val zipCode: Int = 0,

    @com.google.gson.annotations.SerializedName("registration_proof")
    val registrationProof: Uri? = null,

    @com.google.gson.annotations.SerializedName("business_hours")
    val businessHours: BusinessHoursDto = BusinessHoursDto(),

    @com.google.gson.annotations.SerializedName("device_token")
    val deviceToken: String  = "",

    @com.google.gson.annotations.SerializedName("type")
    val type: String  = "",

    @com.google.gson.annotations.SerializedName("social_id")
    val socialId: String  = ""

) : Parcelable{

    @kotlinx.parcelize.Parcelize
    data class BusinessHoursDto(

        @com.google.gson.annotations.SerializedName("mon")
        val mon: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("tue")
        val tue: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("wed")
        val wed: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("thu")
        val thu: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("fri")
        val fri: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("sat")
        val sat: List<String> = emptyList(),

        @com.google.gson.annotations.SerializedName("sun")
        val sun: List<String> = emptyList()

    ) : Parcelable

}
//
//fun RegisterRequestDto.toDomain() : RegisterRequest {
//    return RegisterRequest(
//        fullName = fullName,
//        email = email,
//        phone = phone,
//        password = password,
//        role = role,
//        businessName = businessName,
//        informalName = informalName,
//        address = address,
//        city = city,
//        state = state,
//        zipCode = zipCode.toString(),
//        registrationProof = registrationProof,
//        businessHours = businessHours.toDomain(),
//        deviceToken = deviceToken,
//        type = type,
//        socialId = socialId
//    )
//}

fun RegisterRequestDto.BusinessHoursDto.toDomain() : RegisterRequest.BusinessHours {
    return RegisterRequest.BusinessHours(
        mon = mon,
        tue = tue,
        wed = wed,
        thu = thu,
        fri = fri,
        sat = sat,
        sun = sun
    )
}

