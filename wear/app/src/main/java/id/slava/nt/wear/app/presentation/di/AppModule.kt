package id.slava.nt.wear.app.presentation.di

import id.slava.nt.wear.app.presentation.PlrunApp
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val appModule = module {
    single {
        (androidApplication() as PlrunApp).applicationScope
    }
}