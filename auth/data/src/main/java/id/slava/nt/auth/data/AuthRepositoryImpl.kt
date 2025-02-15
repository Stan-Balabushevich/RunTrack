package id.slava.nt.auth.data


import id.slava.nt.auth.domain.AuthRepository
import id.slava.nt.core.data.networking.post
import id.slava.nt.core.domain.AuthInfo
import id.slava.nt.core.domain.SessionStorage
import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.EmptyResult
import id.slava.nt.core.domain.util.Result
import id.slava.nt.core.domain.util.asEmptyDataResult
import io.ktor.client.HttpClient

class AuthRepositoryImpl(
    private val httpClient: HttpClient,
    private val sessionStorage: SessionStorage
): AuthRepository {

    override suspend fun login(email: String, password: String): EmptyResult<DataError.Network> {
        val result = httpClient.post<LoginRequest, LoginResponse>(
            route = "/login",
            body = LoginRequest(
                email = email,
                password = password
            )
        )
        if(result is Result.Success) {
            sessionStorage.set(
                AuthInfo(
                    accessToken = result.data.accessToken,
                    refreshToken = result.data.refreshToken,
                    userId = result.data.userId
                )
            )
        }
        return result.asEmptyDataResult()
    }

    override suspend fun register(email: String, password: String): EmptyResult<DataError.Network> {
        return httpClient.post<RegisterRequest, Unit>(
            route = "/register",
            body = RegisterRequest(
                email = email,
                password = password
            )
        )
    }
}