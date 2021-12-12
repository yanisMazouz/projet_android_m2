package fr.ccm.m1.android.projet_android.dataSource

import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ccm.m1.android.projet_android.model.ItsThisForThatRoom

@Dao
interface ItsThisForThatDao {

    @Query("SELECT * FROM ItsThisForThat")
    fun selectAll() : LiveData<List<ItsThisForThatRoom>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(itsThisForThatRoom: ItsThisForThatRoom)


    @Query("DELETE FROM ItsThisForThat")
    fun deleteAll()

    @Query("DELETE FROM ItsThisForThat WHERE id = :id")
    fun delete(id: Long)
}