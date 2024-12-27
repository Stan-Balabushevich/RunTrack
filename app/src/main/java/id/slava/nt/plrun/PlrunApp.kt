package id.slava.nt.plrun

import android.app.Application
import android.content.Context
import android.util.Log
import com.google.android.play.core.splitcompat.SplitCompat
import com.google.firebase.FirebaseApp

import id.slava.nt.auth.data.di.authDataModule
import id.slava.nt.auth.presentation.di.authViewModelModule
import id.slava.nt.core.connectivity.data.di.coreConnectivityDataModule
import id.slava.nt.core.data.di.coreDataModule
import id.slava.nt.core.database.di.databaseModule
import id.slava.nt.plrun.di.appModule
import id.slava.nt.run.data.di.runDataModule
import id.slava.nt.run.location.di.locationModule
import id.slava.nt.run.network.di.networkModule
import id.slava.nt.run.presentation.di.runPresentationModule
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

        // Initialize Firebase
        FirebaseApp.initializeApp(this)
        if (FirebaseApp.getInstance() != null) {
            Log.d("PlrunApp", "Firebase initialized successfully")
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@PlrunApp)
            workManagerFactory()
            modules(
                authDataModule,
                authViewModelModule,
                appModule,
                coreDataModule,
                runPresentationModule,
                locationModule,
                databaseModule,
                networkModule,
                runDataModule,
                coreConnectivityDataModule
            )
        }
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        SplitCompat.install(this)
    }
}