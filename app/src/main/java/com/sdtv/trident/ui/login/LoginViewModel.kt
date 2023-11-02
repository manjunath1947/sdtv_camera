package com.sdtv.trident.ui.login

import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.data.LoginDataClass
import com.sdtv.trident.data.OtpDataClass
import com.sdtv.trident.domain.LoginInterface
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern
import kotlin.math.log

class LoginViewModel : ViewModel() {
    // TODO: Implement the ViewModel
    private var _emailFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val emailFlow: MutableStateFlow<String> = _emailFlow

    private var _passwordFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val passwordFlow: MutableStateFlow<String> = _passwordFlow

    private var _responseFlow: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val responseFlow: MutableStateFlow<OtpEnumClass> = _responseFlow

    private var _serverErrorFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val serverErrorFlow: MutableStateFlow<String> = _serverErrorFlow

    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailFlow.value).matches()

    fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile(            "^" +
                "(?=.*\\d)" + //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{8,32}" +               //at least 8 characters
                "$"
        )
      return passwordREGEX.matcher(password).matches()
             }
    fun startLogin():LoginDataClass{
        var userlogin = LoginDataClass(
            emailFlow.value,
            passwordFlow.value
        )
        Log.d("user",userlogin.toString())


        if (!emailFlow.value.isValidEmail()){
            responseFlow.value = OtpEnumClass.EMAILERROR
        }

        else if (!isValidPasswordFormat(passwordFlow.value)){
            responseFlow.value = OtpEnumClass.PASSWORD_ERROR
        }
        else if (userlogin.email.length > 1 || userlogin.password.length  > 1 ) {
            Log.d("success", userlogin.email.length.toString() )
            responseFlow.value = OtpEnumClass.BACKGROUND_BUTTON
        }
        return userlogin

    }
    var retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.27.16.141:9030")
        .build()
    fun message() {
        val userInterface = retrofitBuilder.create(LoginInterface::class.java)
        val  userPost= startLogin()
        val call = userInterface.sendUserData(userPost)
        call.enqueue(object : Callback<LoginDataClass> {
            override fun onResponse(call: Call<LoginDataClass>, response: Response<LoginDataClass>) {
                if (response.code() == 200){
                   responseFlow.value = OtpEnumClass.SUCCESS
//                    Toast.makeText(activity,"logged in successsfully", Toast.LENGTH_SHORT).show()
                    Log.d("success",response.code().toString())
                }
                else{
                        responseFlow.value = OtpEnumClass.SERVER_ERROR
                        serverErrorFlow.value = response.errorBody()!!.string()
//                    Toast.makeText(activity,"Invalid credentials ${response.code()}", Toast.LENGTH_SHORT).show()
                    Log.d("fail", response.message())
                }


            }
            override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
                responseFlow.value = OtpEnumClass.ERROR
                serverErrorFlow.value = t.message.toString()
//                Toast.makeText(activity,"unable to reach server", Toast.LENGTH_SHORT).show()
                Log.d("failure", t.message.toString())
            }
        })
    }

    private var _otpvalidation: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val otpvalidation: MutableStateFlow<OtpEnumClass> = _otpvalidation



}
