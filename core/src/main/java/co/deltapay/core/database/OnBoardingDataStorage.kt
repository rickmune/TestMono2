package co.deltapay.core.database

import android.content.Context
import android.content.SharedPreferences
import co.deltapay.core.model.Login
import co.deltapay.core.model.OnBoardingData

class OnBoardingDataStorage(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(FILENAME, Context.MODE_PRIVATE)

    var loginInfo: Login
        get() = Login(
            email = prefs.getString(LOGIN_EMAIL, "").toString(),
            phoneNumber = prefs.getString(LOGIN_PHONE, "").toString()
        )

        set(value) = prefs.edit()
            .putString(LOGIN_EMAIL, value.email)
            .putString(LOGIN_PHONE, value.phoneNumber)
            .apply()
    var onBoardingData: OnBoardingData
        get() = OnBoardingData(
            lang = prefs.getString(LANG, "").toString(),
            docType = prefs.getString(DOC_TYPE, "").toString(),
            firstName = prefs.getString(F_NAME, "").toString(),
            lastName = prefs.getString(L_NAME, "").toString(),
            id = prefs.getString(ID, "").toString(),
            dateOfBirth = prefs.getString(DOB, "").toString(),
            customerId = prefs.getString(CUST_ID, "").toString(),
            documentType = prefs.getString(DOCUMENT_TYPE, "").toString(),
            phoneNumber = prefs.getString(PHONE, "").toString(),
            email = prefs.getString(EMAIL, "").toString()
        )

        set(value) = prefs.edit()
            .putString(LANG, value.lang)
            .putString(DOC_TYPE, value.docType)
            .putString(F_NAME, value.firstName)
            .putString(L_NAME, value.lastName)
            .putString(ID, value.id)
            .putString(DOB, value.dateOfBirth)
            .putString(CUST_ID, value.customerId)
            .putString(DOCUMENT_TYPE, value.documentType)
            .putString(PHONE, value.phoneNumber)
            .putString(EMAIL, value.email)
            .apply()

    companion object {
        private const val FILENAME = "co.deltapay.customer.onboarding_prefs"
        private const val LANG = "co.deltapay.customer.prefs.lang"
        private const val DOC_TYPE = "co.deltapay.customer.prefs.doc_type"
        private const val F_NAME = "co.deltapay.customer.prefs.f_name"
        private const val L_NAME = "co.deltapay.customer.prefs.l_name"
        private const val ID = "co.deltapay.customer.prefs.id"
        private const val DOB = "co.deltapay.customer.prefs.dob"
        private const val CUST_ID = "co.deltapay.customer.prefs.cust_id"
        private const val DOCUMENT_TYPE = "co.deltapay.customer.prefs.document_type"
        private const val PHONE = "co.deltapay.customer.prefs.phone"
        private const val EMAIL = "co.deltapay.customer.prefs.email"


        private const val FRONT_ID = "co.deltapay.customer.prefs.front_id"
        private const val BACK_ID = "co.deltapay.customer.prefs.back_id"
        private const val PASSPORT = "co.deltapay.customer.prefs.passport"
        private const val SELFIE = "co.deltapay.customer.prefs.selfie"

        private const val LOGIN_EMAIL = "co.deltapay.customer.prefs.login_email"
        private const val LOGIN_PHONE = "co.deltapay.customer.prefs.login_phone"



        private var INSTANCE: PreferenceStorage? = null
        private val lock = PreferenceStorage::class.java

        @JvmStatic
        fun getInstance(context: Context): PreferenceStorage {
            synchronized(lock) {
                if (INSTANCE == null) {
                    INSTANCE = PreferenceStorage(context)
                }
                return INSTANCE!!
            }
        }

        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}