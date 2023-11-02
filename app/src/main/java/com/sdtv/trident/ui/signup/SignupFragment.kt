/*
package com.sdtv.trident.ui.signup

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.provider.CalendarContract.Colors
import android.text.Editable
import android.text.TextWatcher
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.constraintlayout.motion.widget.TransitionBuilder.validate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.data.SignUpDataClass
import com.sdtv.trident.databinding.FragmentSignupBinding
import com.sdtv.trident.domain.SignUpInterface
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

@AndroidEntryPoint
open class SignupFragment : Fragment() {

    private lateinit var binding: FragmentSignupBinding

    private val viewModel: SignupViewModel by viewModels()

    //    var password: EditText? = null
    var passwordVisible = false

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignupBinding.inflate(layoutInflater)

        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.27.16.189:8080")
            .build()

        fun message() {
            val signUpInterface = retrofitBuilder.create(SignUpInterface::class.java)
            val signUpUser = SignUpDataClass(
                binding.userNameId.text.toString(),
                binding.emailAddressId.text.toString(),
                binding.passwordId.text.toString(),*/
/*binding.confirmPasswordId.text.toString(),*//*
*/
/*binding.streetLine1Id.text.toString(),binding.streetLine2Id.text.toString(),binding.cityId.text.toString()
            ,binding.stateId.text.toString(),binding.zipCodeId.text.toString(),binding.countryId.text.toString(),*//*

                binding.mobileNumberId.text.toString()
            )

            val call = signUpInterface.sendUserData(signUpUser)

            call.enqueue(object : Callback<SignUpDataClass> {
                @SuppressLint("SuspiciousIndentation")
                override fun onResponse(
                    call: Call<SignUpDataClass>,
                    response: Response<SignUpDataClass>
                ) {
                    Log.d("responsecode", response.errorBody().toString())
                    if (response.code()==200) {

                        Log.d("errorcode", "${response.body()}")
                        */
/*Toast.makeText(activity, "registered successfully ", Toast.LENGTH_SHORT)
                            .show()*//*

                        findNavController().navigate(R.id.action_signupFragment_to_otp)
                    } else {
                        Log.d("failure", "${response.code()}")
                        Toast.makeText(activity, "Please fill correct values in required field", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SignUpDataClass>, t: Throwable) {
                    binding.serverErrorId.text = "not registered successful${t.message}"
                    Log.d("TAG", "not registered successful${t.message}")
                    Toast.makeText(activity, "not registered${t.message}", Toast.LENGTH_SHORT)
                        .show()
                }

            })

        }
        //enable the button when all feilds are filled
        val textWatcher: TextWatcher = object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {}

            @SuppressLint("SuspiciousIndentation")
*/
/*            override fun onTextChanged(charSequence: CharSequence, i: Int, i1: Int, i2: Int) {
                val uname = binding.userNameId!!.text.toString().trim { it <= ' ' }
                val email = binding.emailAddressId!!.text.toString().trim { it <= ' ' }
                val pwd = binding.passwordId!!.text.toString().trim { it <= ' ' }
                val cnf_pwd = binding.confirmPasswordId!!.text.toString().trim { it <= ' ' }
                val door_number = binding.streetLine1Id!!.text.toString().trim { it <= ' ' }
                val street2 = binding.streetLine2Id!!.text.toString().trim { it <= ' ' }
                val city = binding.cityId!!.text.toString().trim { it <= ' ' }
                val state = binding.stateId!!.text.toString().trim { it <= ' ' }
                val zipcode = binding.zipCodeId!!.text.toString().trim { it <= ' ' }
                val country = binding.countryId!!.text.toString().trim { it <= ' ' }
                val mobile_number = binding.mobileNumberId!!.text.toString().trim { it <= ' ' }
                *//*
*/
/* if (uname.isEmpty() && email.isEmpty() && pwd.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }*//*
*/
/*
                if (uname.length >= 1) {
                    if ((uname.length >= 1 && email.length >= 1)) {


                        binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton))
                    }
                }

                if (uname.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }

                *//*
*/
/*  if (!email.isEmpty() ){
                    if (!uname.isEmpty() && !email.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty() && !mobile_number.isEmpty() ){


                        binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton))
                    }
                }
                if (email.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }

                if (!pwd.isEmpty() ){
                    if (!uname.isEmpty() && !email.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty() && !mobile_number.isEmpty() ){


                        binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton))
                    }
                }
                if (pwd.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }

                if (!cnf_pwd.isEmpty() ){
                    if (!uname.isEmpty() && !email.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty() && !mobile_number.isEmpty() ){


                        binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton))
                    }
                }
                if (cnf_pwd.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }

                if (!mobile_number.isEmpty() ){
                    if (!uname.isEmpty() && !email.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty() && !mobile_number.isEmpty() ){


                        binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton))
                    }
                }
                if (mobile_number.isEmpty()) {
                    binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.backgroundforverifybutton))
                }
*//*
*/
/*
                *//*
*/
/* binding.registerButtonId!!.isEnabled = !uname.isEmpty() *//*
*/
/**//*
*/
/*&& !email.isEmpty() && !pwd.isEmpty() && !cnf_pwd.isEmpty() && !door_number.isEmpty()
                                                        !street2.isEmpty() && !city.isEmpty() && !state.isEmpty() && !zipcode.isEmpty() && !country.isEmpty()
                                                        !mobile_number.isEmpty()*//*
*/
/**//*
*/
/*
                binding.registerButtonId.setBackground(getResources().getDrawable(R.drawable.afterclickverifybutton)) *//*
*/
/*
            }

            override fun afterTextChanged(editable: Editable) {}
        }*//*

        binding.userNameId.addTextChangedListener(textWatcher)
        binding.emailAddressId.addTextChangedListener(textWatcher)

        //passowrd enable/disable
        binding.passwordId.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            val Right = 2
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.rawX >= binding.passwordId.getRight() - binding.passwordId.getCompoundDrawables()[Right].bounds.width()) {
                    val selection = binding.passwordId.getSelectionEnd()
                    passwordVisible = if (passwordVisible) {
                        binding.passwordId.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_off_24,
                            0
                        )
                        binding.passwordId.setTransformationMethod(PasswordTransformationMethod.getInstance())
                        false
                    } else {
                        binding.passwordId.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_24,
                            0
                        )
                        binding.passwordId.setTransformationMethod(HideReturnsTransformationMethod.getInstance())
                        true
                    }
                    binding.passwordId.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            false
        })

        //confirm password enable/disable
        binding.confirmPasswordId.setOnTouchListener(View.OnTouchListener { view, motionEvent ->
            val Right = 2
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                if (motionEvent.rawX >= binding.confirmPasswordId.getRight() - binding.confirmPasswordId.getCompoundDrawables()[Right].bounds.width()) {
                    val selection = binding.confirmPasswordId.getSelectionEnd()
                    passwordVisible = if (passwordVisible) {
                        binding.confirmPasswordId.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_off_24,
                            0
                        )
                        binding.confirmPasswordId.setTransformationMethod(
                            PasswordTransformationMethod.getInstance()
                        )
                        false
                    } else {
                        binding.confirmPasswordId.setCompoundDrawablesRelativeWithIntrinsicBounds(
                            0,
                            0,
                            R.drawable.baseline_visibility_24,
                            0
                        )
                        binding.confirmPasswordId.setTransformationMethod(
                            HideReturnsTransformationMethod.getInstance()
                        )
                        true
                    }
                    binding.confirmPasswordId.setSelection(selection)
                    return@OnTouchListener true
                }
            }
            false
        })

        //viewmodel call
//        var viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

//field validation
        fun validate(){
            if (binding.userNameId.text.toString().isEmpty()){
                binding.userNameId.error="username is required"
            }else if ( binding.userNameId.text!!.length <=6){
                binding.userNameId.error="username length shouldnot be less than 6 characters"
            }else if (binding.emailAddressId.text.toString().isEmpty()){
                binding.emailAddressId.error="email field shouldn't be empty"
            }else if (!binding.emailAddressId.text.toString().contains("@")) {
                binding.emailAddressId.error = "email should contain @"
            } */
/*else if (!binding.emailAddressId.text.toString().contains(".com")){
                    binding.emailAddressId.error="email should contain .com"
            }*//*
else if (binding.passwordId.text.toString().isEmpty()){
                binding.passwordId.error="email field shouldn't be empty"
            }else if (binding.passwordId.text!!.length <=7){
                binding.passwordId.error="password should contain atleast 7 characters"
            }else if (!binding.passwordId.text.toString().contains("@") )  {
                binding.passwordId.error = "password should cotain special characters like @"
            }
            */
/*else if (!binding.passwordId.text.toString().contains("1")){
                    binding.passwordId.error="password should contain atleast one numeric value"*//*
*/
/*
            }*//*
else if (binding.passwordId.text.toString() != binding.confirmPasswordId.text.toString()){
                binding.confirmPasswordId.error="password and confirm password should match"
            }else if (binding.streetLine1Id.text.toString().isEmpty()){
                binding.streetLine1Id.error="address shouldn't be empty"
            }else if (binding.mobileNumberId.text.toString().length != 10){
                binding.mobileNumberId.error="mobile number should be 10 digits"
            }
            else{
                message()
            }


    }
         */
/*fun isValidPassword(): Boolean {
            if (binding.passwordId.text.toString().length   < 8) return false
            if (binding.passwordId.text.toString().filter { it.isDigit() }.firstOrNull() == null) return false
            if (binding.passwordId.text.toString().filter { it.isLetter() }.filter { it.isUpperCase() }.firstOrNull() == null) return false
            if (binding.passwordId.text.toString().filter { it.isLetter() }.filter { it.isLowerCase() }.firstOrNull() == null) return false
            if (binding.passwordId.text.toString().filter { !it.isLetterOrDigit() }.firstOrNull() == null) return false

            return true
        }

        binding.passwordId.setOnClickListener(){
                isValidPassword()
        }
*//*

        //viewmodel call
//        val viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        binding.registerButtonId.setOnClickListener(){
//                message()

            validate()

        }

        return binding.root
    }
}*/
