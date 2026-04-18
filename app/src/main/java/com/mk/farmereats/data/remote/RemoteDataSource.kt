package com.mk.farmereats.data.remote

import com.mk.farmereats.data.remote.api.ApiService
import com.mk.farmereats.domain.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val api: ApiService
) {
    suspend fun register(
        data : Map<String , RequestBody> ,
        socialId : RequestBody,
        file :  MultipartBody.Part?
    ): Response<ResponseBody> {
        return api.registerUser(data, socialId, file)
    }

    suspend fun login(
        data :  Map<String , RequestBody>
    ) : Response<ResponseBody> {
        return api.login(data)
    }


}