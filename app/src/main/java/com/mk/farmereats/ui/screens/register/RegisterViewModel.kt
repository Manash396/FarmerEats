package com.mk.farmereats.ui.screens.register

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.domain.repository.AuthRepository
import com.mk.farmereats.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val authRepository: AuthRepository ,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(RegisterUiState())
    val state = _state.asStateFlow()


    fun updateForm(newForm: RegisterRequest) {
        _state.update { it.copy(form = newForm) }
    }
    fun clearError() {
        _state.value = _state.value.copy(error = null)
    }

    fun register(context: Context, request: RegisterRequest) {
        viewModelScope.launch {

            _state.update { it.copy(isLoading = true, error = null) }

            val result = authRepository.register(context, _state.value.form)

            _state.update {
                if (result.isSuccess) {

                    val token = result.getOrNull()
                    token?.let {  dataStore.saveToken(it) }

                    it.copy(
                        isLoading = false,
                        isSuccess = true,
                        token = token
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