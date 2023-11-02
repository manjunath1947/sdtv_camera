package com.sdtv.trident.domain

import com.sdtv.trident.data.LoginDataClass
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginInterface  {
    @POST("/login")
    fun sendUserData(
        @Body userPost: LoginDataClass
    ): Call<LoginDataClass>
}