package id.slava.nt.run.network

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import id.slava.nt.core.domain.run.RemoteRunDataSource
import id.slava.nt.core.domain.run.Run
import id.slava.nt.core.domain.util.DataError
import id.slava.nt.core.domain.util.EmptyResult
import kotlinx.coroutines.tasks.await
import id.slava.nt.core.domain.util.Result

class FirestoreRemoteRunDataSource(
    private val firestore: FirebaseFirestore,
    private val firebaseStorage: FirebaseStorage,
    private val firebaseAuth: FirebaseAuth
) : RemoteRunDataSource {

    private fun getUserId(): String? {
        return firebaseAuth.currentUser?.uid
    }

    override suspend fun getRuns(): Result<List<Run>, DataError.Network> {
        return try {
            val userId = getUserId() ?: return Result.Error(DataError.Network.UNAUTHORIZED)

            val snapshot = firestore.collection("users")
                .document(userId)
                .collection("runs")
                .get()
                .await()

            val runs = snapshot.documents.mapNotNull { it.data?.toRun() }
            Result.Success(runs)
        } catch (e: Exception) {
            Result.Error(e.toDataError())
        }
    }

    override suspend fun postRun(run: Run, mapPicture: ByteArray): Result<Run, DataError.Network> {
        return try {
            val userId = getUserId() ?: return Result.Error(DataError.Network.UNAUTHORIZED)
            val runId = run.id ?: return Result.Error(DataError.Network.UNAUTHORIZED)

            val userRunsCollection = firestore.collection("users").document(userId).collection("runs")
//            val runId = userRunsCollection.document().id

            val mapPictureRef = firebaseStorage.reference.child("users/$userId/runs/${runId}/map_pictures/$runId.jpg")
            mapPictureRef.putBytes(mapPicture).await()
            val mapPictureUrl = mapPictureRef.downloadUrl.await().toString()

            val updatedRun = run.copy(mapPictureUrl = mapPictureUrl)
            val runMap = updatedRun.toFirestoreMap()

            userRunsCollection.document(runId).set(runMap).await()

            Result.Success(updatedRun)
        } catch (e: Exception) {
            Result.Error(e.toDataError())
        }
    }

    override suspend fun deleteRun(id: String): EmptyResult<DataError.Network> {
        return try {
            val userId = getUserId() ?: return Result.Error(DataError.Network.UNAUTHORIZED)

            val userRunsCollection = firestore.collection("users").document(userId).collection("runs")
            userRunsCollection.document(id).delete().await()

            // Clean up associated map picture in storage
            val mapPictureRef = firebaseStorage.reference.child("users/$userId/runs/$id/map_pictures/$id.jpg")
            mapPictureRef.delete().await()

            Result.Success(Unit)
        } catch (e: Exception) {
            Result.Error(e.toDataError())
        }
    }
}
