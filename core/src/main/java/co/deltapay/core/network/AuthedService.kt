package co.deltapay.core.network

interface AuthedService {}
/*

    @GET("v1/customers/{customerId}/current-limits")
    suspend fun fetchCurrentLimits(@Path("customerId") customerId: String): ApiResponse<CustomerLimitResponse>

    @GET("/v1/customers/{customerId}/limit-history")
    suspend fun fetchLimitHistory(@Path("customerId") customerId: String): ApiResponse<List<LimitHistoryResponseItem>>

    @GET("/v1/customers/{customerId}/loans")
    suspend fun fetchLoans(@Path("customerId") customerId: String): ApiResponse<CustomerLoans>

    @GET("/v1/customers/loan-products")
    suspend fun fetchLoanProducts(): ApiResponse<LoanProducts>

    @GET("/v1/customers/{customerId}/loans/{loanId}")
    suspend fun fetchLoanDetails(
        @Path("customerId") customerId: String,
        @Path("loanId") loanId: String
    ): ApiResponse<LoanDetail>

    @POST("/v1/customers/{customerId}/loan-request")
    suspend fun requestLoan(
        @Path("customerId")
        customerId: String,
        @Body loanRequest: LoanRequest
    ): ApiResponse<LoanRequestResponse>

    @POST("/v1/customers/{customerId}/loans/{loanId}/pay-loan")
    suspend fun payLoan(
        @Path("customerId") customerId: String,
        @Path("loanId") loanId: String,
        @Body paymentRequest: PaymentRequest
    ): ApiResponse<PaymentResponse>

    @POST("/v1/customers/{reference}/loan-repayment-status")
    suspend fun checkLoanPaymentStatus(
        @Path("customerId") customerId: String,
        @Body loanRequest: LoanRequest
    ): ApiResponse<PaymentStatusResponse>

    @GET("/v1/merchants")
    suspend fun fetchMerchants(): ApiResponse<List<MerchantsResponseItem>>

    @GET("/v1/merchants")
    suspend fun fetchMerchantsFilter(
        @Query("id") id: String?,
        @Query("businessName") businessName: String?,
        @Query("typeOfBusiness") typeOfBusiness: String?
    ): ApiResponse<List<MerchantFilterResponseItem>>

    @GET("/v1/loan-payments")
    suspend fun fetchTransactions(
        @Query("customerId") customerId: String
    ): ApiResponse<List<TransactionResponse>>

    @GET("/v1/customers/{reference}/loan-repayment-status")
    suspend fun checkPaymentStatus(
        @Path("reference") customerId: String
    ): ApiResponse<PaymentStatusResponse>

}

data class CustomerLimitResponse(
    val attributes: CustomerLimit?,
    val name: String?
)

data class CustomerLimit(
    val currentLimit: Float,
    //val effectiveDate: Date,
    val headRoom: Float,
    val utilization: Float
)

data class LimitHistoryResponseItem(
    val attributes: CustomerLimit,
    val name: String
)

data class LoansResponseItem(
    val attributes: Loans,
    val name: String
)

data class Loans(
    val amountDisbursed: Float,
    val amountPaid: Float,
    val balance: Float,
    val interest: Float,
    val id: String,
    val loanAmount: Float,
    val loanProduct: LoanProduct,
    val startDate: Date,
    val dueDate: Date,
    val datePaid: Date,
    val currency: String,
    val merchantName: String
)

data class LoanProduct(
    val attributes: LoansProductAttrib,
    val name: String
)

data class LoansProductAttrib(
    val description: String,
    val id: String,
    val productCode: String
)

data class MerchantsResponseItem(
    val attributes: MerchantAttributes,
    val name: String
)

data class MerchantAttributes(
    val businessName: String,
    val createdAt: String,
    val createdBy: String,
    val deleted: Boolean,
    val deletedAt: Any,
    val email: String,
    val id: String,
    val phone: String,
    val status: String,
    val typeOfBusiness: String,
    val updatedAt: Any
)

data class MerchantFilterResponseItem(
    val attributes: MerchantFilter,
    val name: String
)

data class MerchantFilter(
    val businessName: String,
    val id: String,
    val typeOfBusiness: String
)
*/
