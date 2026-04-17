package com.mk.farmereats.domain.model

import android.net.Uri
import com.mk.farmereats.data.remote.dto.RegisterRequestDto

data class RegisterRequest(
    val fullName: String = "",
    val email: String = "",
    val phone: String = "",
    val password: String = "",
    val role: String = "",
    val businessName: String = "",
    val informalName: String = "",
    val address: String = "",
    val city: String = "",
    val state: String = "",
    val zipCode: String = "",
    val registrationProof: Uri? = null,
    val businessHours: BusinessHours = BusinessHours(),
    val deviceToken: String = "",
    val type: String = "",
    val socialId: String = ""
){
    data class BusinessHours(
        val mon: List<String> = emptyList(),
        val tue: List<String> = emptyList(),
        val wed: List<String> = emptyList(),
        val thu: List<String> = emptyList(),
        val fri: List<String> = emptyList(),
        val sat: List<String> = emptyList(),
        val sun: List<String> = emptyList()
    )
}








//fun RegisterRequest.toDto(): RegisterRequestDto {
//    return RegisterRequestDto(
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
//        zipCode = zipCode.toIntOrNull() ?: 0,
//        registrationProof = registrationProof,
//        businessHours = businessHours.toDto(),
//        deviceToken = deviceToken,
//        type = type,
//        socialId = socialId
//    )
//}
//
//fun RegisterRequest.BusinessHours.toDto(): RegisterRequestDto.BusinessHoursDto {
//    return RegisterRequestDto.BusinessHoursDto(
//        mon = mon,
//        tue = tue,
//        wed = wed,
//        thu = thu,
//        fri = fri,
//        sat = sat,
//        sun = sun
//    )
//}
