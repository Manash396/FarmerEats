package com.mk.farmereats.ui.screens.register

import com.mk.farmereats.domain.model.RegisterRequest

data class RegisterUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val token : String? = null,
    val error: String? = null,
    val form: RegisterRequest = RegisterRequest()
)
