package co.deltapay.onboarding.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.deltapay.core.database.OnBoardingDataStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val onBoardingPref: OnBoardingDataStorage
    ): ViewModel() {

    private val _state = MutableStateFlow<SplashState>(SplashState.Idle)
    val state: StateFlow<SplashState>
        get() = _state

    init {
        viewModelScope.launch {
            val info = onBoardingPref.loginInfo
            if (info.email != "" || info.phoneNumber != "")
                _state.value = SplashState.Log
            else
                _state.value = SplashState.Reg
        }
    }
}

sealed class SplashState {
    object Idle: SplashState()
    object Reg: SplashState()
    object Log: SplashState()
}