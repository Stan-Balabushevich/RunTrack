package id.slava.nt.wear.app.presentation

import android.app.Application
import id.slava.nt.core.connectivity.data.di.coreConnectivityDataModule
import id.slava.nt.wear.app.presentation.di.appModule
import id.slava.nt.wear.run.data.di.wearRunDataModule
import id.slava.nt.wear.run.presentation.di.wearRunPresentationModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PlrunApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PlrunApp)
            modules(
                appModule,
                wearRunPresentationModule,
                wearRunDataModule,
                coreConnectivityDataModule
            )
        }
    }
}