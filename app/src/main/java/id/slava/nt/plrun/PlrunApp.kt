package id.slava.nt.plrun

import android.app.Application
import android.content.Context
import com.google.android.play.core.splitcompat.SplitCompat

import id.slava.nt.auth.data.di.authDataModule
import id.slava.nt.auth.presentation.di.authViewModelModule
import id.slava.nt.core.data.di.coreDataModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.workmanager.koin.workManagerFactory
import org.koin.core.context.startKoin
import timber.log.Timber

class PlrunApp: Application() {

    val applicationScope = CoroutineScope(SupervisorJob())

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PlrunApp)
//            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
//                appModule,
                coreDataModule,
//                runPresentationModule,
//                locationModule,
//                databaseModule,
//                networkModule,
//                runDataModule,
//                coreConnectivityDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}