package com.mk.farmereats.ui.screens.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.domain.repository.AuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()


    fun updateForm(newForm: RegisterRequest) {
        _state.update { it.copy(form = newForm) }
    }

    fun register(context: Context, request: RegisterRequest) {
        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, error = null) }

            val result = authRepository.register(context, request)

            _state.update {
                if (result.isSuccess) {
                    it.copy(
                        isLoading = false,
                        isSuccess = true
                    )
                } else {
                    it.copy(
                        isLoading = false,
                        error = result.exceptionOrNull()?.message
                    )
                }
            }
        }
    }
}