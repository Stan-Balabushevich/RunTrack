package id.slava.nt.run.network.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import id.slava.nt.core.domain.run.RemoteRunDataSource
import id.slava.nt.run.network.FirestoreRemoteRunDataSource
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val networkModule = module {

    single { FirebaseFirestore.getInstance() }
    single { FirebaseStorage.getInstance() }
    single { FirebaseAuth.getInstance() }
    singleOf(::FirestoreRemoteRunDataSource).bind<RemoteRunDataSource>()

//    singleOf(::KtorRemoteRunDataSource).bind<RemoteRunDataSource>()
}