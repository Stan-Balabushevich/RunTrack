package id.slava.nt.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import id.slava.nt.core.database.dao.RunDao
import id.slava.nt.core.database.dao.RunPendingSyncDao
import id.slava.nt.core.database.entity.DeletedRunSyncEntity
import id.slava.nt.core.database.entity.RunEntity
import id.slava.nt.core.database.entity.RunPendingSyncEntity

@Database(
    entities = [
        RunEntity::class,
        RunPendingSyncEntity::class,
        DeletedRunSyncEntity::class
    ],
    version = 1
)
abstract class RunDatabase : RoomDatabase() {

    abstract val runDao: RunDao
    abstract val runPendingSyncDao: RunPendingSyncDao
//    abstract val analyticsDao: AnalyticsDao
}