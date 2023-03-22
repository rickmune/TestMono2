package co.deltapay.home.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
//import co.deltapay.common.ui.components.Balance
import co.deltapay.common.utils.ViewState
import co.deltapay.core.database.OnBoardingDataStorage
import co.deltapay.core.repository.NoAuthRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject
constructor(
    private val onBoardingPref: OnBoardingDataStorage,
    private val noAuthRepository: NoAuthRepository
) : ViewModel() {

    private val _pending = MutableStateFlow<PendingState>(PendingState.Idle)
    val pending: StateFlow<PendingState>
        get() = _pending

    private val _homeState = MutableStateFlow<HomeState>(HomeState.Idle)
    val homeState: StateFlow<HomeState>
        get() = _homeState

    fun getProfileStatus() {
        viewModelScope.launch {
            noAuthRepository.verificationStatus(onBoardingPref.onBoardingData.customerId)
                .collect { it ->
                    when (it) {
                        is ViewState.Success -> {
                            _pending.value = PendingState.StateReceived(
                                emailVerification = it.data.emailVerification,
                                phoneVerification = it.data.phoneVerification,
                                idVerification = it.data.idVerification,
                                documentVerification = it.data.documentVerification
                            )
                        }
                        ViewState.Idle -> {}
                        is ViewState.Error -> {
                            it.errorCode?.message?.let {
                                _pending.value = PendingState.Error(it)
                            }
                        }
                        else -> {}
                    }
                }
        }
    }

    fun goHome() {
        _pending.value = PendingState.GoHome
    }
    fun goToMono() {
        _pending.value = PendingState.Mono
    }

    fun getLimits() {
        viewModelScope.launch {

        }
    }
}

sealed class PendingState {
    object Idle : PendingState()
    data class StateReceived(
        val emailVerification: String,
        val phoneVerification: String,
        val idVerification: String,
        val documentVerification: String
    ) : PendingState()

    data class Customer(val username: String) : PendingState()
    data class Error(val error: String) : PendingState()
    object GoHome : PendingState()
    object Mono : PendingState()
}

sealed class HomeState {
    object Idle : HomeState()
    //data class Limit(val balance: Balance) : HomeState()
}
