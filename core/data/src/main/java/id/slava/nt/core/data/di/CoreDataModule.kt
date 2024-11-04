package id.slava.nt.core.data.di

import android.content.SharedPreferences
import id.slava.nt.core.data.networking.HttpClientFactory
import org.koin.dsl.module


val coreDataModule = module {
    single {
        HttpClientFactory().build()
    }
//    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()
//
//    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}