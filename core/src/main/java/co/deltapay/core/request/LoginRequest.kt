package co.deltapay.core.request

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    @Json(name = "password") val password: String,
    @Json(name = "username") val username: String
) : Parcelable
