package id.slava.nt.core.connectivity.data.di

import id.slava.nt.core.connectivity.data.WearNodeDiscovery
import id.slava.nt.core.connectivity.data.messaging.WearMessagingClient
import id.slava.nt.core.connectivity.domain.NodeDiscovery
import id.slava.nt.core.connectivity.domain.messaging.MessagingClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val coreConnectivityDataModule = module {
    singleOf(::WearMessagingClient).bind<MessagingClient>()
    singleOf(::WearNodeDiscovery).bind<NodeDiscovery>()
}