package com.sdtv.trident.ui.welcome

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.databinding.FragmentWelcomeBinding
import com.sdtv.trident.ui.login.LoginViewModel
import com.sdtv.trident.ui.signup.SignupViewModel
import kotlinx.coroutines.flow.collect

class Welcome : Fragment() {

    companion object {
        fun newInstance() = Welcome()
    }
//    private val signupViewModel:SignupViewModel by viewModels()
    private val loginViewModel:LoginViewModel by viewModels()
    private lateinit var viewModel: WelcomeViewModel
    private lateinit var binding: FragmentWelcomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentWelcomeBinding.inflate(layoutInflater)
        Log.d("wel",binding.welcomeId.text.toString())
        suspend fun onbuttonclick() {
            loginViewModel.responseFlow.collect() {
                when(it) {
                  //  OtpEnumClass.EMAILTEXT -> binding.welcomeId.text = loginViewModel.emailFlow.value

                    else -> {}
                }
            }
        }
        binding.welcomeBackButton.setOnClickListener {
            findNavController().navigate(R.id.action_welcome_to_login)
        }
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WelcomeViewModel::class.java)


        // TODO: Use the ViewModel
    }


}