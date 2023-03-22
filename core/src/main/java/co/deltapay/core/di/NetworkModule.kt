package co.deltapay.core.di

import android.content.Context
import co.deltapay.core.BuildConfig
import co.deltapay.core.network.NoAuthService
import com.chuckerteam.chucker.api.ChuckerCollector
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.chuckerteam.chucker.api.RetentionManager
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.skydoves.sandwich.coroutines.CoroutinesResponseCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Qualifier
import javax.inject.Singleton

/**
 * Main entry point for network access.
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    var gson: Gson = GsonBuilder()
        .setDateFormat("yyyy-MM-DD hh:mm:ss").create()

    //var mosh: Moshi = Moshi.Builder().build()

    /**
     * Build the Moshi object that Retrofit will be using, making sure to add the Kotlin adapter for
     * full Kotlin compatibility.
     */
    @Singleton
    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()


    @Provides
    @Singleton
    fun provideOkHttpClient(context: Context): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .callTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)

        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            httpClient.addInterceptor(logging)
        }
        val chuckForbidden = listOf("production")
        val collector = ChuckerCollector(
            context = context,
            showNotification = true,
            retentionPeriod = RetentionManager.Period.ONE_HOUR
        )
        if (!chuckForbidden.contains(BuildConfig.FLAVOR))
            httpClient.addInterceptor(
                ChuckerInterceptor.Builder(context)
                    .collector(collector)
                    .maxContentLength(250000L)
                    .redactHeaders(emptySet())
                    .alwaysReadResponseBody(true)
                    .build()
            )
        return httpClient.build()
    }

    // Configure retrofit to parse JSON using moshi and use rxJava
    @Singleton
    @UnAuthenticatedRetrofit
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            //.addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()

    @Singleton
    @AuthenticatedRetrofit
    @Provides
    fun provideAuthenticatedRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()

    // Configure retrofit to parse JSON using moshi and use rxJava
    @Singleton
    @UnAuthenticatedDocRetrofit
    @Provides
    fun unAuthenticatedDocRetrofit(okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(BuildConfig.DOC_API_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutinesResponseCallAdapterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideNoAuthService(@UnAuthenticatedRetrofit retrofit: Retrofit): NoAuthService =
        retrofit.create(NoAuthService::class.java)

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnAuthenticatedRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class AuthenticatedRetrofit

    @Qualifier
    @Retention(AnnotationRetention.BINARY)
    annotation class UnAuthenticatedDocRetrofit
}