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

    private val runsCollection = firestore.collection("runs")
    private  val storageRef = firebaseStorage.reference

    override suspend fun getRuns(): Result<List<Run>, DataError.Network> {
        return try {
            val userId = firebaseAuth.currentUser?.uid ?: return Result.Error(DataError.Network.UNAUTHORIZED)

            val snapshot = runsCollection
                .whereEqualTo("userId", userId) // Filter runs by userId
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
            val userId = firebaseAuth.currentUser?.uid
                ?: return Result.Error(DataError.Network.UNAUTHORIZED) // Handle case where user is not authenticated

            val runId = runsCollection.document().id

            // Reference for map picture with the new structure
            val mapPictureRef = storageRef.child("users/$userId/runs/$runId/map_pictures/$runId.jpg")

            // Upload the picture to Firebase Storage
            mapPictureRef.putBytes(mapPicture).await()

            // Get the download URL for the uploaded picture
            val mapPictureUrl = mapPictureRef.downloadUrl.await().toString()

            // Update the Run object with the mapPictureUrl
            val updatedRun = run.copy(mapPictureUrl = mapPictureUrl)

            // Save the run data in Firestore, under the user's runs subcollection
            val userRunsCollection = firestore.collection("users").document(userId).collection("runs")
            val runMap = updatedRun.toFirestoreMap()

            userRunsCollection.document(runId).set(runMap).await()

            Result.Success(updatedRun) // Return updated run with the URL
        } catch (e: Exception) {
            Result.Error(e.toDataError())
        }
    }


    override suspend fun deleteRun(id: String): EmptyResult<DataError.Network> {
        return try {
            val userId = firebaseAuth.currentUser?.uid
                ?: return Result.Error(DataError.Network.UNAUTHORIZED) // Handle case where user is not authenticated

            // Delete the run from the user's subcollection
            val userRunsCollection = firestore.collection("users").document(userId).collection("runs")
            userRunsCollection.document(id).delete().await()

            Result.Success(Unit) // Successfully deleted the run
        } catch (e: Exception) {
            Result.Error(e.toDataError()) // Handle errors and convert them to DataError
        }
    }

}
