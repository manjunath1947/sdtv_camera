package com.sdtv.trident.domain

import com.sdtv.trident.data.SignUpDataClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpInterface {
    @POST("/signup")
    fun sendUserData(
        @Body signUpDataClass: SignUpDataClass
    ):Call<SignUpDataClass>


}