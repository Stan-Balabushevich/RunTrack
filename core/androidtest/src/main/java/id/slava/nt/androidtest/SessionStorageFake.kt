package id.slava.nt.androidtest

import id.slava.nt.core.domain.AuthInfo
import id.slava.nt.core.domain.SessionStorage

class SessionStorageFake: SessionStorage {

    private var authInfo: AuthInfo? = null

    override suspend fun get(): AuthInfo? {
        return authInfo
    }

    override suspend fun set(info: AuthInfo?) {
        authInfo = info
    }

}