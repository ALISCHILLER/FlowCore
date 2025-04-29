package com.msa.core.base

import com.msa.core.data.network.handler.NetworkHandler
import com.msa.core.data.network.handler.NetworkResult

/**
 * یک کلاس پایه برای تمامی Repository‌ها.
 */
abstract class BaseRepository(val networkHandler: NetworkHandler) {

    /**
     * تابع عمومی برای انجام درخواست GET.
     */
    protected suspend inline fun <reified T> safeGetRequest(url: String): NetworkResult<T> {
        return networkHandler.getRequest(url)
    }

    /**
     * تابع عمومی برای انجام درخواست POST.
     */
    protected suspend inline fun <reified T> safePostRequest(url: String, body: Any): NetworkResult<T> {
        return networkHandler.postRequest(url, body)
    }

    /**
     * تابع عمومی برای انجام درخواست PUT.
     */
    protected suspend inline fun <reified T> safePutRequest(url: String, body: Any): NetworkResult<T> {
        return networkHandler.putRequest(url, body)
    }

    /**
     * تابع عمومی برای انجام درخواست DELETE.
     */
    protected suspend fun safeDeleteRequest(url: String): NetworkResult<Unit> {
        return networkHandler.deleteRequest(url)
    }
}