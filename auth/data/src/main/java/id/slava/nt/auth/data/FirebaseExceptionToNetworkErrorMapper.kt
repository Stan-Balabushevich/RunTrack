package id.slava.nt.auth.data

import com.google.firebase.FirebaseNetworkException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.FirebaseAuthRecentLoginRequiredException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import id.slava.nt.core.domain.util.DataError

fun mapFirebaseExceptionToNetworkError(exception: Exception): DataError.Network {
    return when (exception) {
        is FirebaseAuthInvalidUserException -> DataError.Network.UNAUTHORIZED
        is FirebaseAuthInvalidCredentialsException -> DataError.Network.UNAUTHORIZED
        is FirebaseAuthUserCollisionException -> DataError.Network.CONFLICT
        is FirebaseTooManyRequestsException -> DataError.Network.TOO_MANY_REQUESTS
        is FirebaseNetworkException -> DataError.Network.REQUEST_TIMEOUT
        is FirebaseAuthRecentLoginRequiredException -> DataError.Network.UNAUTHORIZED
        else -> DataError.Network.UNKNOWN
    }
}
