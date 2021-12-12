package fr.ccm.m1.android.projet_android.architecture

import com.google.gson.GsonBuilder
import fr.ccm.m1.android.projet_android.remote.ItsThisForThatEndPoint
import fr.ccm.m1.android.projet_android.remote.PandaEndPoint
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private val retrofitItsThisForThat: Retrofit = Retrofit.Builder()
        .baseUrl("http://itsthisforthat.com/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getItsThisForThat(): ItsThisForThatEndPoint = retrofitItsThisForThat.create(ItsThisForThatEndPoint::class.java)

    private val retrofitPanda: Retrofit = Retrofit.Builder()
        .baseUrl("https://some-random-api.ml/animal/")
        .addConverterFactory(GsonConverterFactory.create(GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()))
        .build()

    fun getPanda(): PandaEndPoint = retrofitPanda.create(PandaEndPoint::class.java)


}