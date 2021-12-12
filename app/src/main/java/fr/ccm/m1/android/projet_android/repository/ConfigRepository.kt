package fr.ccm.m1.android.projet_android.repository

import fr.ccm.m1.android.projet_android.BuildConfig

class ConfigRepository {

    fun getAppVersion(): Int {
        return  BuildConfig.VERSION_CODE;
    }
}