package id.slava.nt.run.data.di

import id.slava.nt.core.domain.run.SyncRunScheduler
import id.slava.nt.run.data.CreateRunWorker
import id.slava.nt.run.data.DeleteRunWorker
import id.slava.nt.run.data.FetchRunsWorker
import id.slava.nt.run.data.SyncRunWorkerScheduler
import org.koin.androidx.workmanager.dsl.workerOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val runDataModule = module {
    workerOf(::CreateRunWorker)
    workerOf(::FetchRunsWorker)
    workerOf(::DeleteRunWorker)

    singleOf(::SyncRunWorkerScheduler).bind<SyncRunScheduler>()
//    singleOf(::PhoneToWatchConnector).bind<WatchConnector>()
}