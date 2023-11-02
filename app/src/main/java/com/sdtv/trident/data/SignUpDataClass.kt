package com.sdtv.trident.data

import com.google.gson.annotations.SerializedName

data class SignUpDataClass(
    @SerializedName("username")val username: String,
    @SerializedName("email")val email: String,
    @SerializedName("password")val password: String,
//    @SerializedName("confirm")val confirm_password: String,
    @SerializedName("mobile_number") val mobile_number:String,
   /* @SerializedName("door_number") val door_number:String,
    @SerializedName("street_name") val street_name:String,
    @SerializedName("district") val district:String,
    @SerializedName("state") val state:String,
    @SerializedName("nationality") val nationalty:String,
    @SerializedName("pincode") val pincode:String,*/
)
