package com.mk.farmereats.data.remote

import com.mk.farmereats.data.remote.api.ApiService
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun register(
        data : Map<String , RequestBody> ,
        file :  MultipartBody.Part?
    ): Response<Unit> {
        return api.registerUser(data, file)
    }


}