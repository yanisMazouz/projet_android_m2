package fr.ccm.m1.android.projet_android.repository

import androidx.lifecycle.LiveData
import fr.ccm.m1.android.projet_android.architecture.CustomApplication
import fr.ccm.m1.android.projet_android.architecture.RetrofitBuilder
import fr.ccm.m1.android.projet_android.model.ItsThisForThatRetrofit
import fr.ccm.m1.android.projet_android.model.ItsThisForThatRoom
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ItsThisForThatRepository {
    private val mItsThisForThatDao = CustomApplication.instance.mApplicationDatabase.itsThisForThatDao()

    fun selectAllItsThisForThat(): LiveData<List<ItsThisForThatRoom>> {
        return mItsThisForThatDao.selectAll()
    }


    private suspend fun insertItsThisForThat(itsThisForThatRoom: ItsThisForThatRoom) =
        withContext(Dispatchers.IO) {
            mItsThisForThatDao.insert(itsThisForThatRoom)
        }

    suspend fun deleteItsThisForThat(id: Long) =
        withContext(Dispatchers.IO) {
            mItsThisForThatDao.delete(id)
        }


    suspend fun deleteAllItsThisForThat() = withContext(Dispatchers.IO) {
        mItsThisForThatDao.deleteAll()
    }


    suspend fun fetchData() {
        insertItsThisForThat(RetrofitBuilder.getItsThisForThat().getRandom().toRoom())
    }

}
private fun ItsThisForThatRetrofit.toRoom(): ItsThisForThatRoom {
    return ItsThisForThatRoom(
        itsThis = itsThis,
        forThat = forThat,
        timestamp = System.currentTimeMillis()
    )
}
