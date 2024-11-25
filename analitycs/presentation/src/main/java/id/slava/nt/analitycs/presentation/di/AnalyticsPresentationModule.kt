package id.slava.nt.analitycs.presentation.di

import id.slava.nt.analitycs.presentation.AnalyticsDashboardViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val analyticsPresentationModule = module {
    viewModelOf(::AnalyticsDashboardViewModel)
}