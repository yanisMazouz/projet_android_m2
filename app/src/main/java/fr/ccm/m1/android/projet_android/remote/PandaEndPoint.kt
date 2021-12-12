package fr.ccm.m1.android.projet_android.remote

import fr.ccm.m1.android.projet_android.model.PandaRetrofit
import retrofit2.http.GET

interface PandaEndPoint {
    @GET("red_panda")
    suspend fun getRandom() : PandaRetrofit

}