package com.mk.farmereats.data.repository

import android.content.Context
import com.mk.farmereats.data.remote.RemoteDataSource
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.domain.model.toMultipart
import com.mk.farmereats.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remote : RemoteDataSource
) : AuthRepository{

    override suspend fun register(
        context: Context,
        request: RegisterRequest
    ) : Result<Unit>{
        return try {
            val (data, file) = request.toMultipart(context)

            val response = remote.register(data, file)

            if (response.isSuccessful) {
                Result.success(Unit)
            } else {
                Result.failure(Exception("API Error"))
            }

        }catch (e : Exception){
            Result.failure(e)
        }
    }
}