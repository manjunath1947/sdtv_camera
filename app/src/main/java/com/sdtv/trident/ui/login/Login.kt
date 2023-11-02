package com.sdtv.trident.ui.login

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.data.LoginDataClass
import com.sdtv.trident.databinding.FragmentLoginBinding
import com.sdtv.trident.domain.LoginInterface
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Login : Fragment() {

    companion object {
        fun newInstance() = Login()
    }
    private lateinit var binding: FragmentLoginBinding
    private val loginViewModel:LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = loginViewModel

        /*
        var retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("http://10.27.16.141:9030")
            .build()
        fun message() {
            val userInterface = retrofitBuilder.create(LoginInterface::class.java)
            val userPost = LoginDataClass(binding.emailid.text.toString(), binding.password.text.toString())
            val call = userInterface.sendUserData(userPost)
            call.enqueue(object : Callback<LoginDataClass> {
                override fun onResponse(call: Call<LoginDataClass>, response: Response<LoginDataClass>) {
                    if (response.code() == 200){
                        findNavController().navigate(R.id.action_login_to_welcome)
                        Toast.makeText(activity,"logged in successsfully",Toast.LENGTH_SHORT).show()
                        Log.d("success",response.code().toString())
                    }
                    else{
                        Toast.makeText(activity, response.errorBody()!!.string(),Toast.LENGTH_SHORT).show()
                        Log.d("fail", response.errorBody()!!.string())
                    }


                }
                override fun onFailure(call: Call<LoginDataClass>, t: Throwable) {
                    Toast.makeText(activity,"unable to reach server",Toast.LENGTH_SHORT).show()
                    Log.d("failure", t.message.toString())
                }
            })
        }*/


      suspend fun onButtonClick(){
            loginViewModel.message()
            loginViewModel.responseFlow.collect{
                when(it){
                        OtpEnumClass.SUCCESS ->if (findNavController().currentDestination?.id == R.id.login){
                            findNavController().navigate(R.id.action_login_to_welcome)
                            Toast.makeText(activity,"Logged in Successfully",Toast.LENGTH_SHORT).show()
                        }
                    OtpEnumClass.EMAILERROR -> binding.emailid.error = "Please enter correct email Id"
                    OtpEnumClass.PASSWORD_ERROR -> binding.password.error = "Please Enter Correct Password"
                        OtpEnumClass.SERVER_ERROR -> Toast.makeText(activity,loginViewModel.serverErrorFlow.value,Toast.LENGTH_SHORT).show()
                        OtpEnumClass.ERROR -> Toast.makeText(activity,loginViewModel.serverErrorFlow.value,Toast.LENGTH_SHORT).show()
                    OtpEnumClass.BACKGROUND_BUTTON ->  binding.loginbutton.background = ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.afterclickverifybutton, null)
                    else -> {

                    }
                }
            }
        }
        binding.loginbutton.setOnClickListener(){
            lifecycleScope.launch {
                onButtonClick()
            }

        }
        binding.signuphere.setOnClickListener{
            findNavController().navigate(R.id.action_login_to_signupFragment1)
        }
/*        binding.button.setOnClickListener(){
            message()
            findNavController().navigate(R.id.action_login2_to_signup2)
        }*/


        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_login, container, false)
        return binding.root
    }



}