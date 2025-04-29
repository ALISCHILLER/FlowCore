package com.msa.core.data.network.handler

import android.util.Log
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.observer.ResponseObserver
import io.ktor.client.request.*
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

/**
 * یک شیء Singleton برای مدیریت درخواست‌های شبکه.
 */
object NetworkHandler {

    /**
     * HttpClient با تنظیمات پیشرفته.
     */
    private  val httpClient = HttpClient(OkHttp) {
        // Serializer برای تبدیل JSON
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    encodeDefaults = false
                }
            )
        }


        // لاگ‌گیری برای Debugging
        install(Logging) {
            logger = object : Logger {
                override fun log(message: String) {
                    Log.v("NetworkHandler", message)
                }
            }
            level = LogLevel.ALL
        }

        // مدیریت Timeout
        install(io.ktor.client.plugins.HttpTimeout) {
            requestTimeoutMillis = 15_000L // 15 ثانیه
            connectTimeoutMillis = 15_000L
            socketTimeoutMillis = 15_000L
        }

        // مشاهده وضعیت پاسخ‌ها
        install(ResponseObserver) {
            onResponse { response ->
                Log.d("HTTP Status", "${response.status.value}")
            }
        }

        // Headerهای مشترک
        defaultRequest {
            contentType(ContentType.Application.Json)
            accept(ContentType.Application.Json)
        }
    }

    /**
     * تابع عمومی برای دسترسی به HttpClient.
     */
    public fun getHttpClient(): HttpClient {
        return httpClient
    }
    /**
     * تابع مرکزی برای انجام درخواست‌های شبکه و مدیریت خطاهای احتمالی.
     */
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): NetworkResult<T> {
        return try {
            withContext(Dispatchers.IO) {
                val response = apiCall()
                NetworkResult.Success(response)
            }
        } catch (exception: Exception) {
            Log.e("NetworkHandler", "Error occurred: ${exception.message}")
            NetworkResult.Error(exception, exception.localizedMessage ?: "Unknown error")
        }
    }

    /**
     * تابعی برای انجام درخواست GET.
     */
    suspend inline fun <reified T> getRequest(url: String): NetworkResult<T> {
        return safeApiCall {
            getHttpClient().get(url) {
                contentType(ContentType.Application.Json)
            }.body()
        }
    }

    /**
     * تابعی برای انجام درخواست POST.
     */
    suspend inline fun <reified T> postRequest(url: String, body: Any): NetworkResult<T> {
        return safeApiCall {
            getHttpClient().post(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
        }
    }

    /**
     * تابعی برای انجام درخواست PUT.
     */
    suspend inline fun <reified T> putRequest(url: String, body: Any): NetworkResult<T> {
        return safeApiCall {
            getHttpClient().put(url) {
                contentType(ContentType.Application.Json)
                setBody(body)
            }.body()
        }
    }

    /**
     * تابعی برای انجام درخواست DELETE.
     */
    suspend fun deleteRequest(url: String): NetworkResult<Unit> {
        return safeApiCall {
            httpClient.delete(url)
        }
    }
}