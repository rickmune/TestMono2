package co.deltapay.core.database

import android.content.Context
import android.content.SharedPreferences

class PreferenceStorage(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_FILENAME, Context.MODE_PRIVATE)

    var language: String?
        get() = prefs.getString(LANG, null)
        set(value) = prefs.edit().putString(LANG, value).apply()

    var token: String?
        get() = prefs.getString(TOKEN,null)
        set(value) = prefs.edit().putString(TOKEN,value).apply()

    companion object {
        private const val PREFS_FILENAME = "co.deltapay.customer.prefs"
        private const val LANG = "co.deltapay.customer.prefs.lang"
        private const val TOKEN = "co.deltapay.customer.prefs.token"

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