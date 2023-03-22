package co.deltapay.core.network

import co.deltapay.core.request.LoginRequest
import co.deltapay.core.response.LoginResponse
import co.deltapay.core.response.ProfileStatusResponse
import com.skydoves.sandwich.ApiResponse
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface NoAuthService {

    @POST("/v1/customers/user-login")
    suspend fun userLogin(@Body request: LoginRequest): ApiResponse<LoginResponse>

    @POST("/v1/customers/{customerId}/query-verification-status")
    suspend fun verificationStatus(@Path("customerId") customerId: String): ApiResponse<ProfileStatusResponse>
}
