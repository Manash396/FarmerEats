package com.mk.farmereats.data.remote.api

import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.PartMap



interface ApiService {

    @Multipart
    @POST("register")
    suspend fun registerUser(
        @PartMap data: Map<String, @JvmSuppressWildcards RequestBody>,
        @Part registrationProof: MultipartBody.Part?
    ): Response<Unit>
}