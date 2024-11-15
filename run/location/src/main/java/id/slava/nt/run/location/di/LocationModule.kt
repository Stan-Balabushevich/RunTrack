package id.slava.nt.run.location.di

import id.slava.nt.run.location.AndroidLocationObserver
import id.slava.nt.run.domain.LocationObserver
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val locationModule = module {
    singleOf(::AndroidLocationObserver).bind<LocationObserver>()
}