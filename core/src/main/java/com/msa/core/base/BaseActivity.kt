package com.msa.core.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel

/**
 * یک کلاس پایه برای تمامی Activity‌ها.
 */

abstract class BaseActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ProvideDependencies {
                SetContent()
            }
        }
    }

    /**
     * تابعی برای تنظیم محتوای UI (Compose).
     */
    @Composable
    abstract fun SetContent()

    /**
     * تابعی برای فراهم کردن وابستگی‌ها (Dependency Injection).
     */
    @Composable
    open fun ProvideDependencies(content: @Composable () -> Unit) {
        content()
    }

    /**
     * تابعی برای ایجاد ViewModel.
     */
//    inline fun <reified VM : ViewModel> getViewModel(): VM {
//        return viewModel(viewModelClass = VM::class.java)
//    }

    /**
     * Context محلی برای Compose.
     */
    @Composable
    fun LocalContext() = LocalContext.current
}