package com.sdtv.trident.ui.signup

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sdtv.trident.data.SignUpDataClass
//import com.sdtv.trident.databinding.FragmentSignupBinding
import com.sdtv.trident.domain.SignUpInterface
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject

@HiltViewModel
class SignupViewModel @Inject constructor() : ViewModel() {
    // TODO: Implement the ViewModel
  //  private lateinit var binding: FragmentSignupBinding

    private var _userFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val userFlow: MutableStateFlow<String> = _userFlow
fun message(){

}
}