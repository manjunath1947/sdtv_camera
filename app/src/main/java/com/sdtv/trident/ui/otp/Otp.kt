package com.sdtv.trident.ui.otp
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.sdtv.trident.R
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.databinding.FragmentOtpBinding
import kotlinx.coroutines.launch

class Otp : Fragment() {
    // Assign bindings to fragments
private lateinit var binding: FragmentOtpBinding
private val viewModel: OtpViewModel by viewModels ()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOtpBinding.inflate(layoutInflater)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding.timerTextview.text = "seconds remaining:" + millisUntilFinished / 1000
                binding.verifyButton.isEnabled = true
            }
            override fun onFinish() {
                binding.timerTextview.text = "try it once again"
                binding.verifyButton.isEnabled = false
            }
        }.start()
    val viewModel = ViewModelProvider(this).get(OtpViewModel::class.java)
    binding.lifecycleOwner = this
    Log.d("tag1","$binding.lifecycleOwner" )

        //Validation for verify button
        suspend fun onbuttonclick() {
            viewModel.otpvalidation.collect {
                when(it) {
                    OtpEnumClass.LOADING -> Toast.makeText(activity, "please enter proper otp", Toast.LENGTH_SHORT).show()
                    OtpEnumClass.SUCCESS -> if (findNavController().currentDestination?.id == R.id.otp){
                        findNavController().navigate(R.id.action_otp_to_login)
                    }
                    OtpEnumClass.TOAST_MESSAGE -> Toast.makeText(activity, "Registered Successfully!", Toast.LENGTH_SHORT).show()
                    OtpEnumClass.CHANGE_COLOR -> binding.verifyButton.background = ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.afterclickverifybutton, null)

                    else -> {}
                }
            }
        }

        binding.verifyButton.setOnClickListener {
            viewModel.validation()
            lifecycleScope.launch {
                onbuttonclick()
            }
        }

        suspend fun verifyvalidation() {
            viewModel.verifyvalidation.collect {
                when(it) {
                    OtpEnumClass.CHANGE_COLOR -> binding.verifyButton.background = ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.afterclickverifybutton, null)
                    OtpEnumClass.SUCCESS -> binding.verifyButton.background = ResourcesCompat.getDrawable(requireActivity().resources, R.drawable.backgroundforverifybutton, null)

                    else -> {}
                }
            }
        }

        //First edittextview validation from viewmodel
        suspend fun firstedittext() {
            viewModel.firstOtpDigitvalidation.collect {
                when(it) {
                    OtpEnumClass.FIRST_EDITTEXT ->  binding.secondOtpDigit.requestFocus()
                    OtpEnumClass.LOADING -> binding.firstOtpDigit.requestFocus()
                    else -> {}
                }
            }
        }
        //Second edittextview validation from viewmodel
        suspend fun secondedittext() {
            viewModel.secondOtpDigitvalidation.collect {
                when(it) {
                    OtpEnumClass.SECOND_EDITTEXT ->  binding.thirdOtpDigit.requestFocus()
                    OtpEnumClass.FIFTH_EDITTEXT -> binding.secondOtpDigit.requestFocus()
                    else -> {}
                }
            }
        }

        //Third edittextview validation from viewmodel
        suspend fun thirdedittext() {
            viewModel.thirdOtpDigitvalidation.collect {
                when(it) {
                    OtpEnumClass.THIRD_EDITTEXT ->  binding.forthOtpDigit.requestFocus()
                    OtpEnumClass.SECOND_EDITTEXT -> binding.thirdOtpDigit.requestFocus()
                    else -> {}
                }
            }
        }

        //Fourth edittextview validation from viewmodel
        suspend fun forthedittext() {
            viewModel.forthOtpDigitvalidation.collect {
                when(it) {
                    OtpEnumClass.FORTH_EDITTEXT ->  binding.fiftOtpDigit.requestFocus()
                    OtpEnumClass.THIRD_EDITTEXT -> binding.forthOtpDigit.requestFocus()
                    else -> {}
                }
            }
        }

        //Fifth edittextview validation from viewmodel
        suspend fun fifthedittext() {
            viewModel.fifthOtpDigitvalidation.collect {
                when(it) {
                    OtpEnumClass.FIFTH_EDITTEXT ->  binding.sixthOtpDigit.requestFocus()
                    OtpEnumClass.FORTH_EDITTEXT -> binding.fiftOtpDigit.requestFocus()
                    else -> {}
                }
            }
        }

        //After click firsteditview change to next editview
        binding.firstOtpDigit.addTextChangedListener {
            viewModel.firstedittext()
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                firstedittext()
                verifyvalidation()

            }
        }

        //After click secondeditview change to next editview
        binding.secondOtpDigit.addTextChangedListener {
            viewModel.secondedittext()
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                secondedittext()
                verifyvalidation()
            }
        }

        //After click thirdeditview change to next editview
        binding.thirdOtpDigit.addTextChangedListener {
            viewModel.thirdedittext()
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                thirdedittext()
                verifyvalidation()
            }
        }

        //After click fortheditview change to next editview
        binding.forthOtpDigit.addTextChangedListener {
            viewModel.forthedittext()
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                forthedittext()
                verifyvalidation()
            }
        }
        //After click fiftheditview change to next editview
        binding.fiftOtpDigit.addTextChangedListener {
            viewModel.fifthedittext()
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                fifthedittext()
                verifyvalidation()
            }
        }
        //After click sixtheditview change to next editview
        binding.sixthOtpDigit.addTextChangedListener {
            viewModel.verifybuttonvalidation()
            lifecycleScope.launch {
                verifyvalidation()
            }
        }

    binding.backButton.setOnClickListener {
        findNavController().navigate(R.id.action_welcome_to_signupFragment)
    }

        //backspace for removing variable from second editview
        binding.secondOtpDigit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                viewModel.text1.value = ""
                binding.firstOtpDigit.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        //backspace for removing variable from third editview
        binding.thirdOtpDigit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                viewModel.text1.value = ""
                binding.secondOtpDigit.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        //backspace for removing variable from forth editview
        binding.forthOtpDigit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                viewModel.text1.value = ""
                binding.thirdOtpDigit.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        //backspace for removing variable from fifth editview
        binding.fiftOtpDigit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                viewModel.text1.value = ""
                binding.forthOtpDigit.requestFocus()
                return@setOnKeyListener true
            }
            false
        }

        //backspace for removing variable from sixth editview
        binding.sixthOtpDigit.setOnKeyListener { _, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_DEL && event.action == KeyEvent.ACTION_UP) {
                viewModel.text1.value = ""
                binding.fiftOtpDigit.requestFocus()
                return@setOnKeyListener true
            }
            false
        }
        return binding.root
        }
}