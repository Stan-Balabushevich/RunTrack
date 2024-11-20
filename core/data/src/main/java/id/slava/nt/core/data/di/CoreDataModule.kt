package id.slava.nt.core.data.di

import id.slava.nt.core.data.auth.EncryptedSessionStorage
import id.slava.nt.core.data.networking.HttpClientFactory
import id.slava.nt.core.data.run.OfflineFirstRunRepository
import id.slava.nt.core.domain.SessionStorage
import id.slava.nt.core.domain.run.RunRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module


val coreDataModule = module {
    single {
        HttpClientFactory(sessionStorage = get()).build()
    }
    singleOf(::EncryptedSessionStorage).bind<SessionStorage>()

    singleOf(::OfflineFirstRunRepository).bind<RunRepository>()
}