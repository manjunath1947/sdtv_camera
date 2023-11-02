package com.sdtv.trident.ui.signup

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.ViewModel
import com.sdtv.trident.common.SignUpEnumClass
import com.sdtv.trident.data.SignUpDataClass
import com.sdtv.trident.domain.SignUpInterface
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.regex.Pattern

class SignupFragment1ViewModel : ViewModel() {
    // TODO: Implement the ViewModel
//usernameFlow that reads username
    private  var _usernameFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val usernameFlow: MutableStateFlow<String> = _usernameFlow
//emailFlow that reads email
    private  var _emailFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val emailFlow: MutableStateFlow<String> = _emailFlow
//passwirdFlow that reads password
    private  var _passwordFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val passwordFlow: MutableStateFlow<String> = _passwordFlow
//confirmpasswordFlow that reads password
    private  var _confirmpasswordFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val confirmpasswordFlow: MutableStateFlow<String> = _confirmpasswordFlow
//mobilenumberFlow that reads mobie number
    private  var _mobilenumberFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val mobilenumberFlow: MutableStateFlow<String> = _mobilenumberFlow
//responseFlow that reads response from server
    private  var _responseFlow: MutableStateFlow<SignUpEnumClass> = MutableStateFlow(SignUpEnumClass.INIT)
    val responseFlow: MutableStateFlow<SignUpEnumClass> = _responseFlow
//serverErrorFlow that reads error from server
    private  var _serverResponseFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val serverResponseFlow: MutableStateFlow<String> = _serverResponseFlow

    //email validation using Android Standard Utils,which validates email pattern
    fun CharSequence?.isValidEmail() = !isNullOrEmpty() && Patterns.EMAIL_ADDRESS.matcher(
        emailFlow.value
    ).matches()

    //Password validation using regex,declared one regular expression which validates uppercase letter,lowercase letter,special characters and numbers
    fun isValidPasswordFormat(password: String): Boolean {
        val passwordREGEX = Pattern.compile(
            "^" +
                    "(?=.*\\d)" +         //at least 1 digit
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
//defined startSignUp()  function that have been assigned to variables of Dataclass
    fun startSignUp() : SignUpDataClass {
        val signUpUser = SignUpDataClass(
            usernameFlow.value,
            emailFlow.value,
            passwordFlow.value,
            mobilenumberFlow.value
        )

        Log.d("username", signUpUser.toString())
//conditional statement where username should contain atleast 8 characters
        if (usernameFlow.value.length < 8){

            responseFlow.value = SignUpEnumClass.USERNAME
            Log.d("userlength",usernameFlow.value.length.toString())
        }
        //conditionl statement where email is validated according to isValidEmail() function that have been defined above
        else if (!emailFlow.value.isValidEmail()){
            Log.d("email",emailFlow.value)
            responseFlow.value = SignUpEnumClass.EMAIL

        }
        //password is validated according to isValidPasswordFormat() function that have been defined
        else if (!isValidPasswordFormat(passwordFlow.value)){
            Log.d("pswd",passwordFlow.value.length.toString())
            responseFlow.value = SignUpEnumClass.PASSWORD

        }
    /*    //confirm password shoould match with password
        else if (confirmpasswordFlow.value != passwordFlow.value){
            Log.d("confirm",confirmpasswordFlow.value)
            responseFlow.value = SignUpEnumClass.CONFIRM_PASSWORD
        }*/
        //mobile number length should be 10 digits,else it will be invalid
        else if(mobilenumberFlow.value.length != 10){
            Log.d("mobile",mobilenumberFlow.value.length.toString())
            responseFlow.value = SignUpEnumClass.MOBILE_NUMBER

        }

        return signUpUser
    }

    //retrofit builder where we have assigned baseURL for restAPI calls
    val retrofitBuilder: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("http://10.27.16.141:8080")
        .build()

    //function message which contains onResponse and onFailure methods,so that can communicate with server
    fun message() {
        val signUpInterface = retrofitBuilder.create(SignUpInterface::class.java)
        val signUpUser = startSignUp()
        val call = signUpInterface.sendUserData(signUpUser)

        call.clone().enqueue(
            object : Callback<SignUpDataClass> {
                override  fun onResponse(
                    call: Call<SignUpDataClass>,
                    response: Response<SignUpDataClass>
                ){
                    Log.d("responsecode", response.errorBody().toString())
                    if (response.code()==200 ) {

                        Log.d("success", response.code().toString())
                        responseFlow.value = SignUpEnumClass.SUCCESS

                    } else {
                        Log.d("fail", response.code().toString())
                        responseFlow.value = SignUpEnumClass.SERVERERROR
                        serverResponseFlow.value = response.errorBody()!!.string()
                        Log.d("serverresponse", serverResponseFlow.value)

                    }

                }

                override  fun onFailure(call: Call<SignUpDataClass>, t: Throwable) {
                    Log.d("failure",t.message.toString())
                    responseFlow.value = SignUpEnumClass.ERROR
                    serverResponseFlow.value = t.message.toString()

                }

            })

    }

}