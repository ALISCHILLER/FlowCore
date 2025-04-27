package com.msa.core.utils.logger

import android.content.Context
import android.os.Build
import timber.log.Timber
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.charset.StandardCharsets

object LoggerHelper {

    private var logFile: File? = null

    /**
     * تنظیمات اولیه برای Timber
     * این تابع باید در onCreate کلاس Application فراخوانی شود
     */
    fun init(context: Context) {

//        if (BuildConfig.DEBUG) {
//            Timber.plant(Timber.DebugTree())  // برای محیط توسعه (Debug) از DebugTree استفاده می‌کنیم
//        }

        // ساخت پوشه و فایل لاگ
        val logDir = File(context.filesDir, "logs")
        if (!logDir.exists()) {
            logDir.mkdirs()
        }

        logFile = File(logDir, "error_log.txt")

        // راه‌اندازی Timber با Tree سفارشی برای نوشتن لاگ‌ها به فایل
        Timber.plant(FileLoggingTree(logFile!!))
    }

    // برای لاگ کردن پیام‌های Debug
    fun d(message: String, throwable: Throwable? = null) {
        Timber.d(throwable, message)
    }

    // برای لاگ کردن پیام‌های Info
    fun i(message: String, throwable: Throwable? = null) {
        Timber.i(throwable, message)
    }

    // برای لاگ کردن پیام‌های Warning
    fun w(message: String, throwable: Throwable? = null) {
        Timber.w(throwable, message)
    }

    // برای لاگ کردن پیام‌های Error
    fun e(message: String, throwable: Throwable? = null) {
        Timber.e(throwable, message)
    }

    // برای لاگ کردن پیام‌های Verbose
    fun v(message: String, throwable: Throwable? = null) {
        Timber.v(throwable, message)
    }

    // برای لاگ کردن پیام‌های Assert
    fun wtf(message: String, throwable: Throwable? = null) {
        Timber.wtf(throwable, message)
    }

    // خواندن محتویات فایل لاگ
    fun readLogFile(): String? {
        return logFile?.takeIf { it.exists() }?.readText(StandardCharsets.UTF_8)
    }

    // پاک کردن فایل لاگ
    fun clearLogFile() {
        logFile?.let {
            try {
                if (it.exists()) {
                    it.delete()
                }
            } catch (e: IOException) {
                Timber.e(e, "Error deleting log file")
            }
        }
    }
}


//LoggerHelper.init(applicationContext)
//
//// برای لاگ کردن خطا
//LoggerHelper.e("این یک پیام خطا است!")
//
//// برای لاگ کردن هشدار
//LoggerHelper.w("این یک پیام هشدار است!")
//
//// خواندن محتویات فایل لاگ
//val logContent = LoggerHelper.readLogFile()
//println(logContent)
