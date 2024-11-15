package id.slava.nt.run.presentation.di

import id.slava.nt.run.domain.RunningTracker
import id.slava.nt.run.presentation.active_run.ActiveRunViewModel
import id.slava.nt.run.presentation.run_overview.RunOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val runPresentationModule = module {
    singleOf(::RunningTracker)
    single {
        get<RunningTracker>().elapsedTime
    }

    viewModelOf(::RunOverviewViewModel)
    viewModelOf(::ActiveRunViewModel)
}