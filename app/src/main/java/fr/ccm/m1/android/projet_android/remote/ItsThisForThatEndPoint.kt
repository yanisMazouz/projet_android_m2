package fr.ccm.m1.android.projet_android.remote

import fr.ccm.m1.android.projet_android.model.ItsThisForThatRetrofit
import retrofit2.http.GET

interface ItsThisForThatEndPoint {
    @GET("api.php?json")
    suspend fun getRandom() : ItsThisForThatRetrofit

}