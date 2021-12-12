package fr.ccm.m1.android.projet_android.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import fr.ccm.m1.android.projet_android.repository.FirebaseAuthRepository

class Fonctionnalite3ViewModel : ViewModel() {

    private val mFirebaseAuthRepository: FirebaseAuthRepository by lazy { FirebaseAuthRepository() }
    var mCurrentUser = MutableLiveData<FirebaseUser>()
    var mErrorProcess = MutableLiveData<Int>()


    init {
        mCurrentUser = mFirebaseAuthRepository.mCurrentUser
        mErrorProcess = mFirebaseAuthRepository.mErrorProcess
    }


    fun loginUser(email: String, password: String) {
        mFirebaseAuthRepository.loginUser(email, password)
    }


    fun registerNewUser(email: String, password: String) {
        mFirebaseAuthRepository.registerNewUser(email, password)
    }

    fun disconnect(){
        mFirebaseAuthRepository.disconnectUser()
    }
}

