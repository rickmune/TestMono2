package co.deltapay.core.network

import com.skydoves.sandwich.ApiErrorModelMapper
import com.skydoves.sandwich.ApiResponse
import org.json.JSONObject


// Create your custom error model.
data class ErrorEnvelope(
    val code: Int,
    val message: String
)

// An error response mapper.
// Create an instance of your custom model using the `ApiResponse.Failure.Error` in the `map`.
object ErrorMapper : ApiErrorModelMapper<ErrorEnvelope> {
    override fun map(apiErrorResponse: ApiResponse.Failure.Error<*>): ErrorEnvelope {
        val text = apiErrorResponse.errorBody?.string()
        val message = if (text != null)
            JSONObject(text)["description"].toString()
        else "Unknown Error!"

        return ErrorEnvelope(apiErrorResponse.statusCode.code, message)
    }
}