package co.deltapay.core.repository

import android.util.Log
import co.deltapay.common.utils.ErrorCode
import co.deltapay.common.utils.ViewState
import co.deltapay.core.di.IoDispatcher
import co.deltapay.core.network.NoAuthService
import co.deltapay.core.request.*
import co.deltapay.core.response.*
import com.skydoves.sandwich.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import org.json.JSONObject
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoAuthRepositoryImpl @Inject constructor(
    private val noAuthService: NoAuthService,
    @IoDispatcher private val coroutinesDispatcher: CoroutineDispatcher
) : NoAuthRepository {

    override fun userLogin(request: LoginRequest): Flow<ViewState<LoginResponse>> =
        flow {
            val resp = noAuthService.userLogin(request)
            resp.suspendOnSuccess {
                emit(ViewState.Success(data))
            }.suspendOnError {
                val error = map(MyErrorResponseMapper)
                Log.e("NoAuthRepositoryImpl", "userLogin: $error")
                emit(ViewState.Error(error))
            }.suspendOnException {
                Log.e("NoAuthRepositoryImpl", "userLogin: $this")
                emit(ViewState.Error(ErrorCode(1000, "get userLogin Exception")))
            }
        }.onStart {
            emit(ViewState.Loading)
        }.flowOn(coroutinesDispatcher)

    override fun verificationStatus(customerId: String): Flow<ViewState<ProfileStatusResponse>> =
        flow {
            val resp = noAuthService.verificationStatus(customerId)
            resp.suspendOnSuccess {
                emit(ViewState.Success(data))
            }.suspendOnError {
                val error = map(MyErrorResponseMapper)
                emit(ViewState.Error(error))
            }.suspendOnException {
                Log.e("NoAuthRepositoryImpl", "verificationStatus: $this")
                emit(ViewState.Error(ErrorCode(1000, "get verificationStatus Exception")))
            }
        }.onStart {
            emit(ViewState.Loading)
        }.flowOn(coroutinesDispatcher)

    object ImageResponseMapper : ApiErrorModelMapper<ErrorCode> {
        override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorCode {
            return when(apiErrorResponse.response.code()) {
                404 -> {
                    ErrorCode(-1, "Service unavailable")
                }
                in 500.. 599 -> {
                    ErrorCode(-1, "Service unavailable")
                }
                else -> {
                    var a = "Service error"
                    var c = -1
                    apiErrorResponse.errorBody?.let {
                        val resStr: String = it.string()
                        val json = JSONObject(resStr)
                        a = json.get("details") as String
                        val b = json.get("stack")
                        //return ErrorCode(500, "$a $b")
                    }
                    ErrorCode(c, a)
                }
            }
        }
    }

    object MyErrorResponseMapper : ApiErrorModelMapper<ErrorCode> {
        override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorCode {
            return when(apiErrorResponse.response.code()) {
                404 -> {
                    ErrorCode(-1, "Service unavailable")
                }
                in 500.. 599 -> {
                    ErrorCode(-1, "Service unavailable")
                }
                else -> {
                    var a = "Service error"
                    var c = -1
                    apiErrorResponse.errorBody?.let {
                        val resStr: String = it.string()
                        val json = JSONObject(resStr)
                        a = json.get("description") as String
                        val b = json.get("success")
                        c = json.optInt("errorCode") //json.getInt("errorCode")
                    }
                    ErrorCode(c, a)
                }
            }
        }
    }

}