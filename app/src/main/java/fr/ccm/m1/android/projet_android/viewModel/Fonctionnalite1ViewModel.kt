package fr.ccm.m1.android.projet_android.viewModel

import androidx.lifecycle.ViewModel
import fr.ccm.m1.android.projet_android.repository.ConfigRepository

class Fonctionnalite1ViewModel : ViewModel(){
    private val mFonctionnalite1Repository: ConfigRepository by lazy { ConfigRepository() }
    var mApplicationVersion: Int = mFonctionnalite1Repository.getAppVersion();

}