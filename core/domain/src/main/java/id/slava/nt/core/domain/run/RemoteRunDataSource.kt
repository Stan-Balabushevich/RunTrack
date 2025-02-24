package id.slava.nt.core.domain.run

import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.EmptyResult
import id.slava.nt.core.domain.util.Result

interface RemoteRunDataSource {
    suspend fun getRuns(): Result<List<Run>, DataError.Network>
    suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network>
    suspend fun deleteRun(id: String): EmptyResult<DataError.Network>
}