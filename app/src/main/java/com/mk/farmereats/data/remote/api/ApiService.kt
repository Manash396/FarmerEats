package com.mk.farmereats.data.remote.api

import com.mk.farmereats.domain.model.RegisterResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap



interface ApiService {
    @Multipart
    @POST("user/register")
    suspend fun registerUser(
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part("social_id") socialId : RequestBody,
        @Part registrationProof: MultipartBody.Part?
    ): Response<ResponseBody>

    @Multipart
    @POST("user/login")
    suspend fun login(
        @PartMap data : Map<String , @JvmSuppressWildcards RequestBody>
    ): Response<ResponseBody>
}