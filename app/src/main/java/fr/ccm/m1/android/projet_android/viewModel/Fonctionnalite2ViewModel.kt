package fr.ccm.m1.android.projet_android.viewModel

import androidx.lifecycle.*
import fr.ccm.m1.android.projet_android.model.*
import fr.ccm.m1.android.projet_android.repository.ItsThisForThatRepository
import fr.ccm.m1.android.projet_android.repository.PandaRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class Fonctionnalite2ViewModel : ViewModel(){

    private val mItsThisForThatRepository: ItsThisForThatRepository by lazy { ItsThisForThatRepository() }
    private val mPandaRepository: PandaRepository by lazy { PandaRepository() }

    val mHeaderList = mutableListOf<Header>()

    var mPandaLiveData: LiveData<List<PandaUi>> =
        mPandaRepository.selectAllPanda().map {
            it.toUi()
        }
    var mItsThisForThatLiveData: LiveData<List<ItsThisForThatUi>> =
        mItsThisForThatRepository.selectAllItsThisForThat().map {
            it.toUi()
        }

    fun fetchNewPanda() {
        viewModelScope.launch(Dispatchers.IO) {
            mPandaRepository.fetchData()
        }
    }
    fun fetchNewItsThisForThat() {
        viewModelScope.launch(Dispatchers.IO) {
            mItsThisForThatRepository.fetchData()
        }
    }

    fun deletePanda(pandaUi: PandaUi){
        viewModelScope.launch(Dispatchers.IO) {
            mPandaRepository.deletePanda(pandaUi.id
            )
        }
    }

    fun deleteItsThisForThat(itsThisForThatUi: ItsThisForThatUi){
        viewModelScope.launch(Dispatchers.IO) {
            mItsThisForThatRepository.deleteItsThisForThat(itsThisForThatUi.id
            )
        }
    }

    fun deleteAll() {
        viewModelScope.launch(Dispatchers.IO) {
            mItsThisForThatRepository.deleteAllItsThisForThat()
            mPandaRepository.deleteAllPanda()
        }
    }
}

@JvmName("toUiItsThisForThatRoom")
private fun List<ItsThisForThatRoom>.toUi(): List<ItsThisForThatUi> {
    return asSequence().map {
        ItsThisForThatUi(
            itsThis = it.itsThis,
            forThat = it.forThat,
            timestamp = it.timestamp,
            id = it.id
        )
    }.toList()
}

@JvmName("toUiPandaRoom")
private fun List<PandaRoom>.toUi(): List<PandaUi> {
    return asSequence().map {
        PandaUi(
            fact = it.fact,
            image = it.image,
            timestamp = it.timestamp,
            id = it.id

        )
    }.toList()
}



