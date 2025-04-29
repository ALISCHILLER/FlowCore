package com.msa.core.di
import com.msa.core.utils.Currency
import com.msa.core.utils.EnhancedNumberConverter
import com.msa.core.utils.validation.NationalCodeValidator
import org.koin.dsl.module

val utilsModule = module {
    single { EnhancedNumberConverter }
    single { Currency(0) } // مقدار پیش‌فرض صفر
    single { NationalCodeValidator } // مقدار پیش‌فرض صفر
}