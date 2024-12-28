package id.slava.nt.run.network

import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.storage.StorageException
import id.slava.nt.core.domain.util.DataError
import java.io.IOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

fun Exception.toDataError(): DataError.Network {
    return when (this) {
        is FirebaseFirestoreException -> {
            when (this.code) {
                FirebaseFirestoreException.Code.CANCELLED -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.UNKNOWN -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.INVALID_ARGUMENT -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.DEADLINE_EXCEEDED -> DataError.Network.REQUEST_TIMEOUT
                FirebaseFirestoreException.Code.NOT_FOUND -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.ALREADY_EXISTS -> DataError.Network.CONFLICT
                FirebaseFirestoreException.Code.PERMISSION_DENIED -> DataError.Network.UNAUTHORIZED
                FirebaseFirestoreException.Code.RESOURCE_EXHAUSTED -> DataError.Network.TOO_MANY_REQUESTS
                FirebaseFirestoreException.Code.FAILED_PRECONDITION -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.ABORTED -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.OUT_OF_RANGE -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.UNIMPLEMENTED -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.INTERNAL -> DataError.Network.SERVER_ERROR
                FirebaseFirestoreException.Code.UNAVAILABLE -> DataError.Network.NO_INTERNET
                FirebaseFirestoreException.Code.DATA_LOSS -> DataError.Network.UNKNOWN
                FirebaseFirestoreException.Code.UNAUTHENTICATED -> DataError.Network.UNAUTHORIZED
                FirebaseFirestoreException.Code.OK -> DataError.Network.UNKNOWN
            }
        }
        is StorageException -> {
            when (this.errorCode) {
                StorageException.ERROR_UNKNOWN -> DataError.Network.UNKNOWN
                StorageException.ERROR_OBJECT_NOT_FOUND -> DataError.Network.UNKNOWN
                StorageException.ERROR_BUCKET_NOT_FOUND -> DataError.Network.UNKNOWN
                StorageException.ERROR_PROJECT_NOT_FOUND -> DataError.Network.UNKNOWN
                StorageException.ERROR_QUOTA_EXCEEDED -> DataError.Network.TOO_MANY_REQUESTS
                StorageException.ERROR_NOT_AUTHENTICATED -> DataError.Network.UNAUTHORIZED
                StorageException.ERROR_NOT_AUTHORIZED -> DataError.Network.UNAUTHORIZED
                StorageException.ERROR_RETRY_LIMIT_EXCEEDED -> DataError.Network.REQUEST_TIMEOUT
                StorageException.ERROR_INVALID_CHECKSUM -> DataError.Network.UNKNOWN
                StorageException.ERROR_CANCELED -> DataError.Network.UNKNOWN
                else -> {DataError.Network.UNKNOWN}
            }
        }
        is SocketTimeoutException -> DataError.Network.REQUEST_TIMEOUT
        is UnknownHostException -> DataError.Network.NO_INTERNET
        is IOException -> DataError.Network.SERIALIZATION
        else -> DataError.Network.UNKNOWN
    }
}
