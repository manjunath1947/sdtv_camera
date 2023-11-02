package com.sdtv.trident.data

import com.google.gson.annotations.SerializedName
import java.sql.SQLData

data class OtpDataClass(
    @SerializedName("firstOtpDigit")val firstOtpDigit: String,
    @SerializedName("secondOtpDigit")val secondOtpDigit: String,
    @SerializedName("thirdOtpDigit")val thirdOtpDigit: String,
    @SerializedName("forthOtpDigit")val forthOtpDigit: String,
    @SerializedName("fiftOtpDigit")val fiftOtpDigit: String,
    @SerializedName("sixthOtpDigit")val sixthOtpDigit: String
)