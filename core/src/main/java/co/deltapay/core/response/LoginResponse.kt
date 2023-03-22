package co.deltapay.core.response

data class LoginResponse(
    val success: Boolean,
    val description: String,
    val status: String? = "",
    val customerId: String? = ""
    )
