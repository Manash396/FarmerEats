package com.mk.farmereats.domain.model

import android.content.Context
import android.net.Uri
import com.mk.farmereats.ui.screens.register.getFileName
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File

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
    val type: String = "email",
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

fun RegisterRequest.toMultipart(
    context: Context
): Pair<Map<String, RequestBody>, MultipartBody.Part?> {

    val map = mutableMapOf<String, RequestBody>()

    fun String.toBody() =
        this.toRequestBody("text/plain".toMediaTypeOrNull())

    map["full_name"] = fullName.toBody()
    map["email"] = email.toBody()
    map["phone"] = phone.toBody()
    map["password"] = password.toBody()

    val filePart = registrationProof?.let { uri ->
        val file = uriToFile(context, uri)

        val requestFile = file.asRequestBody("image/*".toMediaTypeOrNull())

        MultipartBody.Part.createFormData(
            "registration_proof",
            file.name,
            requestFile
        )
    }

    return Pair(map, filePart)
}

fun uriToFile(context: Context, uri: Uri): File {
    val contentResolver = context.contentResolver

    val fileName = getFileName(context, uri) ?: "temp_file"

    val file = File(context.cacheDir, fileName)

    contentResolver.openInputStream(uri)?.use { inputStream ->
        file.outputStream().use { outputStream ->
            inputStream.copyTo(outputStream)
        }
    }

    return file
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
