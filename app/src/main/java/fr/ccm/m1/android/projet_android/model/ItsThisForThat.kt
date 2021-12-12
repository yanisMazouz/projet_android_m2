package fr.ccm.m1.android.projet_android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




/** Object use for room */
@Entity(tableName = "ItsThisForThat")
data class ItsThisForThatRoom(
    @ColumnInfo(name = "image")
    val itsThis: String,

    @ColumnInfo(name = "fact")
    val forThat: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for retrofit */
data class ItsThisForThatRetrofit(
    @Expose
    @SerializedName("this")
    val itsThis: String,


    @Expose
    @SerializedName("that")
    val forThat: String
)


