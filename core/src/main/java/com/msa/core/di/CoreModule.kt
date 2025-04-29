package com.msa.core.di

import org.koin.dsl.module
import com.msa.core.utils.file.FileManager
import android.content.Context
import com.google.gson.Gson
import com.msa.core.base.BaseRepository
import com.msa.core.config.AppConfig
import com.msa.core.data.network.handler.NetworkHandler
import com.msa.core.ui.camera.CameraHelper
import com.msa.core.data.storage.BaseSharedPreferences

val CoreModule = module {
    single { FileManager(get()) }

    // ارائه Gson به‌عنوان Singleton
    single { Gson() }

    // ارائه BaseSharedPreferences به‌عنوان Singleton
    single {
        BaseSharedPreferences(
            context = get(),
            prefsName = get<AppConfig>().sharedPreferencesName,
            isEncrypted = true, // استفاده از EncryptedSharedPreferences
            gson = get()
        )
    }

    single { CameraHelper(get()) }


}