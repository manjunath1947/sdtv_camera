package com.sdtv.trident.ui.otp

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.data.OtpDataClass
import kotlinx.coroutines.flow.MutableStateFlow

class OtpViewModel : ViewModel() {
//Declaring Mutable flows
    private var _firstOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val firstOtpDigitFlow: MutableStateFlow<String> = _firstOtpDigitFlow

    private var _secondOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val secondOtpDigitFlow: MutableStateFlow<String> = _secondOtpDigitFlow

    private var _thirdOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val thirdOtpDigitFlow: MutableStateFlow<String> = _thirdOtpDigitFlow

    private var _forthtOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val forthtOtpDigitFlow: MutableStateFlow<String> = _forthtOtpDigitFlow

    private var _fifthOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val fifthOtpDigitFlow: MutableStateFlow<String> = _fifthOtpDigitFlow

    private var _sixthOtpDigitFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val sixthOtpDigitFlow: MutableStateFlow<String> = _sixthOtpDigitFlow

   //Condition check with all edittexts
    private var _otpvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val otpvalidation: MutableStateFlow<OtpEnumClass> = _otpvalidation

    fun validation(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())
        if (otp.firstOtpDigit == "1" && otp.secondOtpDigit == "1" && otp.thirdOtpDigit == "1" && otp.forthOtpDigit == "1" && otp.fiftOtpDigit == "1" && otp.sixthOtpDigit == "1") {
            Log.d("success", otp.toString())
            otpvalidation.value = OtpEnumClass.SUCCESS
            otpvalidation.value = OtpEnumClass.TOAST_MESSAGE

        } else {
            otpvalidation.value = OtpEnumClass.LOADING
        }
        return otp
    }

    //Verify button validation
    private var _verifyvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val verifyvalidation: MutableStateFlow<OtpEnumClass> = _verifyvalidation
    fun verifybuttonvalidation(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())

        if (otp.firstOtpDigit.length == 1 && otp.secondOtpDigit.length == 1 && otp.thirdOtpDigit.length == 1 && otp.forthOtpDigit.length ==1 && otp.fiftOtpDigit.length == 1 && otp.sixthOtpDigit.length == 1) {
            verifyvalidation.value = OtpEnumClass.CHANGE_COLOR
        }
        else if (otp.firstOtpDigit.length == 0 || otp.secondOtpDigit.length == 0 || otp.thirdOtpDigit.length == 0 || otp.forthOtpDigit.length == 0 || otp.fiftOtpDigit.length == 0 || otp.sixthOtpDigit.length == 0) {
            verifyvalidation.value = OtpEnumClass.SUCCESS
        }
        return otp
    }

    //First edittext digit validation
    private var _firstOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val firstOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = _firstOtpDigitvalidation
    fun firstedittext(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())

        if (otp.firstOtpDigit.length == 1) {
            firstOtpDigitvalidation.value = OtpEnumClass.FIRST_EDITTEXT
        }
        if (otp.firstOtpDigit.length == 0) {
            firstOtpDigitvalidation.value = OtpEnumClass.LOADING
        }
        return otp
    }

    //Second edittext digit validation
    private var _secondOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val secondOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = _secondOtpDigitvalidation
    fun secondedittext(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())
        if (otp.secondOtpDigit.length == 1) {
            secondOtpDigitvalidation.value = OtpEnumClass.SECOND_EDITTEXT
        }
        if (otp.secondOtpDigit.length == 0) {
            secondOtpDigitvalidation.value = OtpEnumClass.FIRST_EDITTEXT
        }
        return otp
    }

    //Third edittext digit validation
    private var _thirdOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val thirdOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = _thirdOtpDigitvalidation
    fun thirdedittext(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())
        if (otp.thirdOtpDigit.length == 1) {
            thirdOtpDigitvalidation.value = OtpEnumClass.THIRD_EDITTEXT
        }
        if (otp.thirdOtpDigit.length == 0) {
            thirdOtpDigitvalidation.value = OtpEnumClass.SECOND_EDITTEXT
        }
        return otp
    }

    //Forth edittext digit validation
    private var _forthOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val forthOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = _forthOtpDigitvalidation

    fun forthedittext(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())
        if (otp.forthOtpDigit.length == 1) {
            forthOtpDigitvalidation.value = OtpEnumClass.FORTH_EDITTEXT
        }
        if (otp.forthOtpDigit.length == 0) {
            forthOtpDigitvalidation.value = OtpEnumClass.THIRD_EDITTEXT
        }
        return otp
    }

    //Fifth edittext digit validation
    private var _fifthOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val fifthOtpDigitvalidation: MutableStateFlow<OtpEnumClass> = _fifthOtpDigitvalidation
    fun fifthedittext(): OtpDataClass {
        val otp = OtpDataClass(
            firstOtpDigitFlow.value,
            secondOtpDigitFlow.value,
            thirdOtpDigitFlow.value,
            forthtOtpDigitFlow.value,
            fifthOtpDigitFlow.value,
            sixthOtpDigitFlow.value
        )
        Log.d("Test1", otp.toString())
        if (otp.fiftOtpDigit.length == 1) {
            fifthOtpDigitvalidation.value = OtpEnumClass.FIFTH_EDITTEXT
        }
        else if (otp.fiftOtpDigit.length == 0) {
            fifthOtpDigitvalidation.value = OtpEnumClass.FORTH_EDITTEXT
        }
        return otp
    }






    //Validation for taking backspace to before edittext
    var text1 = MutableLiveData<String>()
    var text2 = MutableLiveData<String>()
    init {
        text1.observeForever {
            text2.value = it
        }
    }

}