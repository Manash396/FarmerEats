package com.mk.farmereats.data.remote.dto

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class RegisterRequestDto(

    @com.google.gson.annotations.SerializedName("full_name")
    val fullName: String,

    @com.google.gson.annotations.SerializedName("email")
    val email: String,

    @com.google.gson.annotations.SerializedName("phone")
    val phone: String,

    @com.google.gson.annotations.SerializedName("password")
    val password: String,

    @com.google.gson.annotations.SerializedName("role")
    val role: String,

    @com.google.gson.annotations.SerializedName("business_name")
    val businessName: String,

    @com.google.gson.annotations.SerializedName("informal_name")
    val informalName: String,

    @com.google.gson.annotations.SerializedName("address")
    val address: String,

    @com.google.gson.annotations.SerializedName("city")
    val city: String,

    @com.google.gson.annotations.SerializedName("state")
    val state: String,

    @com.google.gson.annotations.SerializedName("zip_code")
    val zipCode: Int,

    @com.google.gson.annotations.SerializedName("registration_proof")
    val registrationProof: String,

    @com.google.gson.annotations.SerializedName("business_hours")
    val businessHours: BusinessHoursDto,

    @com.google.gson.annotations.SerializedName("device_token")
    val deviceToken: String,

    @com.google.gson.annotations.SerializedName("type")
    val type: String,

    @com.google.gson.annotations.SerializedName("social_id")
    val socialId: String

) : Parcelable{

    @kotlinx.parcelize.Parcelize
    data class BusinessHoursDto(

        @com.google.gson.annotations.SerializedName("mon")
        val mon: List<String>,

        @com.google.gson.annotations.SerializedName("tue")
        val tue: List<String>,

        @com.google.gson.annotations.SerializedName("wed")
        val wed: List<String>,

        @com.google.gson.annotations.SerializedName("thu")
        val thu: List<String>,

        @com.google.gson.annotations.SerializedName("fri")
        val fri: List<String>,

        @com.google.gson.annotations.SerializedName("sat")
        val sat: List<String>,

        @com.google.gson.annotations.SerializedName("sun")
        val sun: List<String>

    ) : Parcelable

}

