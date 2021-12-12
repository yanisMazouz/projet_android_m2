package fr.ccm.m1.android.projet_android.architecture

import androidx.room.Database
import androidx.room.RoomDatabase
import fr.ccm.m1.android.projet_android.dataSource.ItsThisForThatDao
import fr.ccm.m1.android.projet_android.dataSource.PandaDao
import fr.ccm.m1.android.projet_android.model.ItsThisForThatRoom
import fr.ccm.m1.android.projet_android.model.PandaRoom

@Database(
    entities = [
        ItsThisForThatRoom::class,
        PandaRoom::class
    ],
    version = 4,
    exportSchema = false
)
abstract class CustomRoomDatabase : RoomDatabase() {
    abstract fun itsThisForThatDao() : ItsThisForThatDao
    abstract fun pandaDao() : PandaDao
}
