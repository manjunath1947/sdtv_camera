package com.sdtv.trident.ui.welcome

import androidx.lifecycle.ViewModel
import com.sdtv.trident.common.OtpEnumClass
import com.sdtv.trident.ui.login.LoginViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class WelcomeViewModel : ViewModel() {
    // TODO: Implement the ViewModel

    private var _emailFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val emailFlow: MutableStateFlow<String> = _emailFlow

    private var _passwordFlow: MutableStateFlow<String> = MutableStateFlow(String())
    val passwordFlow: MutableStateFlow<String> = _passwordFlow

    private var _responseFlow: MutableStateFlow<OtpEnumClass> = MutableStateFlow(OtpEnumClass.INIT)
    val responseFlow: MutableStateFlow<OtpEnumClass> = _responseFlow


}