package id.slava.nt.run.network.di

import id.slava.nt.core.domain.run.RemoteRunDataSource
import id.slava.nt.run.network.KtorRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {
    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}