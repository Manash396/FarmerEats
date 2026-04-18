package com.mk.farmereats.domain.repository

import android.content.Context
import com.mk.farmereats.domain.model.RegisterRequest

interface AuthRepository {

    suspend fun register(context: Context, request: RegisterRequest): Result<String>

}