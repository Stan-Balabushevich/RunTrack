package id.slava.nt.auth.presentation.di


import id.slava.nt.auth.presentation.register.RegisterViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val authViewModelModule = module {
    viewModelOf(::RegisterViewModel)
//    viewModelOf(::LoginViewModel)
}