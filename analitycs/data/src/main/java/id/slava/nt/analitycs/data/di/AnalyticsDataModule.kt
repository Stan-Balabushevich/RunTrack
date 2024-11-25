package id.slava.nt.analitycs.data.di

import id.slava.nt.analitycs.data.RoomAnalyticsRepository
import id.slava.nt.analitycs.domain.AnalyticsRepository
import id.slava.nt.core.database.RunDatabase
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val analyticsModule = module {
    singleOf(::RoomAnalyticsRepository).bind<AnalyticsRepository>()
    single {
        get<RunDatabase>().analyticsDao
    }
}