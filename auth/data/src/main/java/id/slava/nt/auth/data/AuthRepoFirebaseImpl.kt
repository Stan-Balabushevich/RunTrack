package id.slava.nt.auth.data

import com.google.firebase.auth.FirebaseAuth
import id.slava.nt.auth.domain.AuthRepository
import id.slava.nt.core.domain.AuthInfo
import id.slava.nt.core.domain.SessionStorage
import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.EmptyResult
import id.slava.nt.core.domain.util.Result
import id.slava.nt.core.domain.util.asEmptyDataResult
import com.google.firebase.auth.AuthResult
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class AuthRepoFirebaseImpl(
    private val sessionStorage: SessionStorage,
    private val auth: FirebaseAuth
) : AuthRepository {

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        return try {
            val authResult = signInWithEmailAndPassword(email, password)
            val user = authResult.user
            if (user != null) {
                // Assuming you have tokens or IDs from Firebase (you might need custom claims for tokens)
                sessionStorage.set(
                    AuthInfo(
//                        accessToken = user.uid, // Firebase doesn't provide access tokens directly
//                        refreshToken = user.uid, // Firebase Auth SDK doesn't expose refresh tokens
                        userId = user.uid
                    )
                )
            }
            Result.Success(Unit).asEmptyDataResult()
        } catch (e: Exception) {
            // Map the exception to your `DataError.Network` or other error types
            val mappedError = mapFirebaseExceptionToNetworkError(e)
            Result.Error(mappedError).asEmptyDataResult()
        }
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return try {
            signUpWithEmailAndPassword(email, password)
            Result.Success(Unit).asEmptyDataResult()
        } catch (e: Exception) {
            val mappedError = mapFirebaseExceptionToNetworkError(e)
            Result.Error(mappedError).asEmptyDataResult()
        }
    }

    private suspend fun signInWithEmailAndPassword(email: String, password: String): AuthResult {
        return suspendCancellableCoroutine { continuation ->
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(task.result!!)
                    } else {
                        continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                    }
                }
        }
    }

    private suspend fun signUpWithEmailAndPassword(email: String, password: String): AuthResult {
        return suspendCancellableCoroutine { continuation ->
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        continuation.resume(task.result!!)
                    } else {
                        continuation.resumeWithException(task.exception ?: Exception("Unknown error"))
                    }
                }
        }
    }
}
