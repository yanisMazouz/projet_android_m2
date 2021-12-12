package fr.ccm.m1.android.projet_android.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName




/** Object use for room */
@Entity(tableName = "Panda")
data class PandaRoom(
    @ColumnInfo(name = "image")
    val image: String,

    @ColumnInfo(name = "fact")
    val fact: String,

    @ColumnInfo(name = "timestamp")
    val timestamp: Long
) {
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}

/** Object use for retrofit */
data class PandaRetrofit(
    @Expose
    @SerializedName("image")
    val image: String,


    @Expose
    @SerializedName("fact")
    val fact: String
)


