package com.mk.farmereats.ui.screens.login


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mk.farmereats.domain.model.LoginRequest
import com.mk.farmereats.domain.repository.AuthRepository
import com.mk.farmereats.utils.DataStoreManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val dataStore: DataStoreManager
) : ViewModel() {

    private val _state = MutableStateFlow(LoginUiState())
    val state: StateFlow<LoginUiState> = _state.asStateFlow()


    fun updateForm(newForm: LoginRequest) {
        _state.update { it.copy(form = newForm) }
    }

    fun clearError(){
        _state.update { it.copy(error = null) }
    }

    fun login() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true, error = null) }

            val result = authRepository.login(_state.value.form)

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