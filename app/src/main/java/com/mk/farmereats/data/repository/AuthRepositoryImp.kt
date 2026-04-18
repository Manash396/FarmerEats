package com.mk.farmereats.data.repository

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.mk.farmereats.data.remote.RemoteDataSource
import com.mk.farmereats.domain.model.LoginRequest
import com.mk.farmereats.domain.model.LoginResponse
import com.mk.farmereats.domain.model.RegisterRequest
import com.mk.farmereats.domain.model.RegisterResponse
import com.mk.farmereats.domain.model.toMultipart
import com.mk.farmereats.domain.repository.AuthRepository
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.toRequestBody
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

            val response = remote.register(data, request.socialId.toRequestBody("text/plain".toMediaTypeOrNull()) , file)

            val raw = response.body()?.string()

            if (response.isSuccessful && raw != null) {
                val obj = Gson().fromJson(raw, RegisterResponse::class.java)

                    if (obj.success){
                        Result.success(obj.token ?: "")
                    }else{
                        Result.failure(Exception(obj.message))
                    }

            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }

        } catch (e: Exception) {
            Log.d("Mkdev", "here in exception${e.message}")
            Result.failure(e)
        }
    }

    override suspend fun login(request: LoginRequest): Result<String> {
        return try {

            val response = remote.login(request.toMultipart())

            val raw = response.body()?.string()


            if (response.isSuccessful && raw != null) {
                val obj = Gson().fromJson(raw, LoginResponse::class.java)

                if (obj.success){
                    Result.success(obj.token ?: "")
                }else{
                    Result.failure(Exception(obj.message))
                }

            } else {
                Result.failure(Exception("HTTP Error: ${response.code()}"))
            }

        }catch (e : Exception){
            Result.failure(e)
        }
    }

}