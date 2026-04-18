package com.mk.farmereats.domain.model

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

data class LoginRequest(
    val email: String = "",
    val password: String = "",
    val role: String = "",
    val deviceToken: String = "",
    val type: String = "email",
    val socialId: String = ""
)

fun LoginRequest.toMultipart() : Map<String, RequestBody>{
    val map = mutableMapOf<String, RequestBody>()

    fun String.toBody() =
        this.toRequestBody("text/plain".toMediaTypeOrNull())

    if (type == "email") {
        map["email"] = email.toBody()
        map["password"] = password.toBody()
    } else {
        map["social_id"] = socialId.toBody()
    }

    map["role"] = role.toBody()
    map["device_token"] = deviceToken.toBody()
    map["type"] = type.toBody()


    return map
}
