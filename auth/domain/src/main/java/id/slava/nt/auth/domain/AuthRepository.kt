package id.slava.nt.auth.domain

import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.EmptyResult


interface AuthRepository {
    suspend fun login(email: String, password: String): EmptyResult<DataError.Network>
    suspend fun register(email: String, password: String): EmptyResult<DataError.Network>
}