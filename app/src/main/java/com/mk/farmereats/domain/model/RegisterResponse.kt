package com.mk.farmereats.domain.model



data class RegisterResponse(
    val success: Boolean,
    val message: String,
    val token: String? = null
)