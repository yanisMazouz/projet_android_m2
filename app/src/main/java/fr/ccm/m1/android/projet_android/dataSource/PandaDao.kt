package fr.ccm.m1.android.projet_android.dataSource


import androidx.lifecycle.LiveData
import androidx.room.*
import fr.ccm.m1.android.projet_android.model.PandaRoom

@Dao
interface PandaDao {

    @Query("SELECT * FROM Panda")
    fun selectAll() : LiveData<List<PandaRoom>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(pandaRoom: PandaRoom)


    @Query("DELETE FROM Panda")
    fun deleteAll()

    @Query("DELETE FROM Panda WHERE id = :id")
    fun delete(id: Long)

}