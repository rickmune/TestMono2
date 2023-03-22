package co.deltapay.core.repository

import co.deltapay.common.utils.ViewState
import co.deltapay.core.request.*
import co.deltapay.core.response.*
import kotlinx.coroutines.flow.Flow

interface NoAuthRepository {

    fun userLogin(request: LoginRequest): Flow<ViewState<LoginResponse>>

    fun verificationStatus(customerId: String): Flow<ViewState<ProfileStatusResponse>>
}