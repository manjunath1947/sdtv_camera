package com.sdtv.trident.data

import com.google.gson.annotations.SerializedName

data class LoginDataClass(
    @SerializedName("email")val email: String,
    @SerializedName("password")val password: String
)
