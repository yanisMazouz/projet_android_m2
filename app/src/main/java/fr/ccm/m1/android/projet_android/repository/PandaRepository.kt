package fr.ccm.m1.android.projet_android.repository

import androidx.lifecycle.LiveData
import fr.ccm.m1.android.projet_android.architecture.CustomApplication
import fr.ccm.m1.android.projet_android.architecture.RetrofitBuilder
import fr.ccm.m1.android.projet_android.model.PandaRetrofit
import fr.ccm.m1.android.projet_android.model.PandaRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PandaRepository {
    private val mPandaDao = CustomApplication.instance.mApplicationDatabase.pandaDao()

    fun selectAllPanda(): LiveData<List<PandaRoom>> {
        return mPandaDao.selectAll()
    }


    private suspend fun insertPanda(pandaRoom: PandaRoom) =
        withContext(Dispatchers.IO) {
            mPandaDao.insert(pandaRoom)
        }

    suspend fun deletePanda(id: Long) =
        withContext(Dispatchers.IO) {
            mPandaDao.delete(id)
        }


    suspend fun deleteAllPanda() = withContext(Dispatchers.IO) {
        mPandaDao.deleteAll()
    }


    suspend fun fetchData() {
        insertPanda(RetrofitBuilder.getPanda().getRandom().toRoom())
    }

}
private fun PandaRetrofit.toRoom(): PandaRoom {
    return PandaRoom(
        image = image,
        fact = fact,
        timestamp = System.currentTimeMillis()
    )
}
