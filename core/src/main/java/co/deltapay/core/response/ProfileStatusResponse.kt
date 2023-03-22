package co.deltapay.core.response

data class ProfileStatusResponse(
    val documentNumber: String,
    val documentVerification: String,
    val documentVerificationStatus: String,
    val emailValid: Boolean,
    val emailVerification: String,
    val idVerification: String,
    val phoneRecommendation: String,
    val phoneVerification: String
)