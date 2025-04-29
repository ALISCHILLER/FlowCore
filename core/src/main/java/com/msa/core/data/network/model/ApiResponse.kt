package com.msa.core.data.network.model



/**
 * مدل پاسخ API.
 */
data class ApiResponse<T>(
    val status: String, // وضعیت پاسخ (مانند "success" یا "error")
    val data: T? = null, // داده‌های بازگشتی از API
    val message: String? = null // پیام مرتبط با پاسخ
)