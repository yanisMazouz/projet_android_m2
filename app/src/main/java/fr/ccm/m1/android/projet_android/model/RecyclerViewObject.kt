package fr.ccm.m1.android.projet_android.model

 abstract class RecyclerViewObject(){
    abstract val timestamp : Long
}

data class Header(
    val date: String,
    override val timestamp: Long
) : RecyclerViewObject()

/** Object use for Ui */
data class PandaUi(
    val image: String,
    val fact: String,
    val id: Long,
    override val timestamp: Long
) : RecyclerViewObject()


/** Object use for Ui */
data class ItsThisForThatUi(
    val itsThis: String,
    val forThat: String,
    val id: Long,
    override val timestamp: Long
) : RecyclerViewObject()