package com.sdtv.trident.ui.signup

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.common.SignUpEnumClass
import com.sdtv.trident.databinding.FragmentSignupFragment1Binding
import kotlinx.coroutines.launch

class SignupFragment1 : Fragment() {
    //variable binding which perfroms databinding operation
    private lateinit var binding: FragmentSignupFragment1Binding
    //variable signupViewModel which calls functions have been defined in viewmodel class
    private val signupViewModel:SignupFragment1ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentSignupFragment1Binding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = signupViewModel



       //suspend function which contains enumclass variables and functionality have been assigned to enumclass variables
        suspend fun onButtonClick(){
           //message function which contains onResponse and onFailure methods
            signupViewModel.message()
//collecting response from sever using resopnseFlow
            signupViewModel.responseFlow.collect{
                when(it){
                    SignUpEnumClass.USERNAME -> binding.userNameId1.error = "username should contain atleast 8 characters"
                    SignUpEnumClass.EMAIL -> binding.emailAddressId1.error = "Please Enter Correct Email Address"
                    SignUpEnumClass.PASSWORD -> binding.passwordId1.error = "Password must contain atleast a\n" + "1.Capital letter\n" +
                            "2.Number\n" +
                            "3.Special charactres like @,#,$\n" +
                            "4.Password must contain atleast 8-32   characters"
                    SignUpEnumClass.MOBILE_NUMBER -> binding.mobileNumberId1.error = "mobile number should be 10 digits"
                    //SignUpEnumClass.CONFIRM_PASSWORD -> binding.confirmPasswordId1.error = "confirm password shouldn't be empty"
                    SignUpEnumClass.SUCCESS -> if (findNavController().currentDestination?.id == R.id.signupFragment1){
                        findNavController().navigate(R.id.action_signupFragment1_to_otp)
                    }
                    SignUpEnumClass.SERVERERROR -> Toast.makeText(activity, signupViewModel.serverResponseFlow.value,
                        Toast.LENGTH_SHORT).show()
                    SignUpEnumClass.ERROR -> Toast.makeText(activity,"unable to connect server: ${signupViewModel.serverResponseFlow.value}",
                        Toast.LENGTH_SHORT).show()

                    else -> {

                    }

                }
            }
        }

        binding.logintextid.setOnClickListener{
            findNavController().navigate(R.id.action_signupFragment1_to_login)
        }



        // button is calling suspend function onButtonClick()
        binding.nextButtonId.setOnClickListener {
            //calling suspend functions using coroutines or another suspend function
            lifecycleScope.launch {
                onButtonClick()
            }
        }
        return binding.root
    }
}