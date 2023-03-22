package co.deltapay.onboarding.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.deltapay.common.utils.ViewState
import co.deltapay.core.database.OnBoardingDataStorage
import co.deltapay.core.database.PreferenceStorage
import co.deltapay.core.model.Login
import co.deltapay.core.repository.NoAuthRepository
import co.deltapay.core.request.LoginRequest
import co.deltapay.core.response.LoginResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject
    constructor(
    private val pref: PreferenceStorage,
    private val onBoardingPref: OnBoardingDataStorage,
    private val noAuthRepository: NoAuthRepository
    ): ViewModel() {

    private val _languageState = MutableStateFlow<ViewState<Boolean>>(ViewState.Idle)
    val languageState: StateFlow<ViewState<Boolean>>
        get() = _languageState

    private val _loginState = MutableStateFlow<LoginStates>(LoginStates.Idle)
    val loginState: StateFlow<LoginStates>
        get() = _loginState

    private val _registerState = MutableStateFlow<ViewState<LoginResponse>>(ViewState.Idle)
    val registerState: StateFlow<ViewState<LoginResponse>>
        get() = _registerState

    init {
        isLoggedIn()
    }

    fun saveLanguage(lang: String) {
        viewModelScope.launch {
            pref.language = lang
            _languageState.tryEmit(ViewState.Success(true))
        }
    }

    fun actionPerformed(actions: LoginActions) {
        when(actions){
            is LoginActions.LoginClicked -> {
                performLogin(actions.username, actions.password)
                //_loginState.value = LoginStates.Success("PENDING")
            }
            else -> {}
        }
    }

    private fun isLoggedIn() {
        val email = onBoardingPref.loginInfo.email
        if(email.isNotEmpty())
            _loginState.value = LoginStates.HasEmail(email)
    }

    private fun performLogin(username: String , password: String) {
        viewModelScope.launch {
            noAuthRepository.userLogin(LoginRequest(password.trim(), username.trim())).collect{
                when(it) {
                    is ViewState.Success -> {
                        if (it.data.success){
                            onBoardingPref.loginInfo = Login(
                                username.trim(),
                                "onBoardingPref.onBoardingData.phoneNumber"
                            )

                            /*val onBoardingData = onBoardingPref.onBoardingData
                            onBoardingData.customerId = it.data.customerId.toString()
                            onBoardingPref.onBoardingData = onBoardingData*/

                            it.data.status?.let { it1 ->
                                _loginState.value = LoginStates.Success(it1)
                            }
                        }
                    }
                    is ViewState.Error -> {
                        _loginState.value = LoginStates.Error(it.errorCode?.message ?: "Login Failed")
                    } else -> {}
                }
            }
        }
    }
}

sealed class LoginActions {

    data class LoginClicked(val username: String , val password: String): LoginActions()
}

sealed class LoginStates {
    object Loading: LoginStates()
    object Idle : LoginStates()
    data class Success(val status: String): LoginStates()
    data class Error(val msg: String): LoginStates()
    data class HasEmail(val email: String): LoginStates()
}