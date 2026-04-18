package com.mk.farmereats.ui.screens.login

import com.mk.farmereats.domain.model.LoginRequest

data class LoginUiState(
    val isLoading: Boolean = false,
    val isSuccess: Boolean = false,
    val token : String? = null,
    val error: String? = null,
    val form : LoginRequest = LoginRequest()
)
