package com.msa.core.data.network.handler

import java.io.IOException
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.TimeoutCancellationException
import timber.log.Timber

/**
 * کلاس برای مدیریت خطاها و تبدیل استثناء‌ها به پیغام‌های قابل فهم برای کاربر یا لاگ‌گیری.
 */
object ErrorHandler {

    /**
     * تبدیل استثناء به یک پیام خطا مشخص.
     *
     * @param exception استثناء رخ داده شده
     * @return پیغام خطا مناسب برای کاربر
     */
    fun handleError(exception: Throwable): String {
        val errorMessage = when (exception) {
            is IOException -> {
                "خطا در اتصال به اینترنت. لطفاً وضعیت اتصال خود را بررسی کنید."
            }
            is TimeoutCancellationException -> {
                "زمان درخواست تمام شده است. لطفاً دوباره امتحان کنید."
            }
            is CancellationException -> {
                "عملیات لغو شده است."
            }
            else -> {
                "خطای ناشناخته"
            }
        }

        // لاگ‌گیری جزئیات خطا
        Timber.e(exception, "An error occurred: $errorMessage")

        return errorMessage
    }

    /**
     * پردازش و مدیریت خطاهای شبکه.
     *
     * @param exception استثناء رخ داده شده
     * @return شیء NetworkResult.Error حاوی پیغام خطا
     */
    fun handleNetworkError(exception: Throwable): NetworkResult.Error {
        val message = handleError(exception)
        return NetworkResult.Error(exception, message)
    }
}