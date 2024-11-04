package id.slava.nt.auth.data.di

import id.slava.nt.auth.data.EmailPatternValidator
import id.slava.nt.auth.domain.PatternValidator
import id.slava.nt.auth.domain.UserDataValidator


import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val authDataModule = module {
    single<PatternValidator> {
        EmailPatternValidator
    }
    singleOf(::UserDataValidator)
//    singleOf(::AuthRepositoryImpl).bind<AuthRepository>()
}