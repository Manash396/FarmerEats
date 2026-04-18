package com.mk.farmereats.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mk.farmereats.data.remote.RemoteDataSource
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.domain.model.RegisterResponse
import com.mk.farmereats.domain.model.toMultipart
import com.mk.farmereats.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImp @Inject constructor(
    private val remote : RemoteDataSource
) : AuthRepository {

    override suspend fun register(
        context: Context,
        request: RegisterRequest
    ): Result<String> {

        return try {

            val (data, file) = request.toMultipart(context)

            val response = remote.register(data, file)

            val raw = response.body()?.string()

            if (response.isSuccessful && raw != null) {

                if (raw.trim().startsWith("{")) {
                    val obj = Gson().fromJson(raw, RegisterResponse::class.java)
                    Result.success(obj.token ?: "")
                }else{
                    Result.failure(Exception(raw))
                }

            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }

        } catch (e: Exception) {
            Log.d("Mkdev", "here in exception${e.message}")
            Result.failure(e)
        }
    }

}