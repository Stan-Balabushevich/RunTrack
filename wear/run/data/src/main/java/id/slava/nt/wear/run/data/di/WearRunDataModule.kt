package id.slava.nt.wear.run.data.di


import id.slava.nt.wear.run.data.HealthServicesExerciseTracker
import id.slava.nt.wear.run.data.WatchToPhoneConnector
import id.slava.nt.wear.run.domain.ExerciseTracker
import id.slava.nt.wear.run.domain.PhoneConnector
import id.slava.nt.wear.run.domain.RunningTracker
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val wearRunDataModule = module {
    singleOf(::HealthServicesExerciseTracker).bind<ExerciseTracker>()
    singleOf(::WatchToPhoneConnector).bind<PhoneConnector>()
    singleOf(::RunningTracker)
    single {
        get<RunningTracker>().elapsedTime
    }
}