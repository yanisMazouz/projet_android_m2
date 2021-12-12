package fr.ccm.m1.android.projet_android.view

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.View
import android.widget.Toast
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.util.StringUtil
import fr.ccm.m1.android.projet_android.R
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite1Binding
import fr.ccm.m1.android.projet_android.databinding.ActivityFonctionnalite2Binding
import fr.ccm.m1.android.projet_android.model.*
import fr.ccm.m1.android.projet_android.viewModel.Fonctionnalite2ViewModel
import java.text.SimpleDateFormat
import java.util.*
import java.util.stream.Collectors

class Fonctionnalite2Activity : AppCompatActivity() {

    private lateinit var viewModel: Fonctionnalite2ViewModel
    private lateinit var binding : ActivityFonctionnalite2Binding
    private lateinit var adapter : Fonctionnalite2Adapter
    private val observerPanda = Observer<List<PandaUi>> {
        adapter.submitList(viewModel.mItsThisForThatLiveData.value?.let { it1 ->
            generateRecyclerList(it,
                it1
            )
        })
    }
    private val observerThisThat = Observer<List<ItsThisForThatUi>> {
        adapter.submitList(viewModel.mPandaLiveData.value?.let { it1 ->
            generateRecyclerList(it,
                it1
            )
        })
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFonctionnalite2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        adapter = Fonctionnalite2Adapter ({ item -> onItemClick(item)}, { item -> onDeleteClick(item)})
        viewModel = ViewModelProvider(this)[Fonctionnalite2ViewModel::class.java]
        binding.recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        binding.recyclerView.adapter = adapter
        binding.buttonAddItem1.setOnClickListener {
            viewModel.fetchNewPanda()
        }
        binding.buttonAddItem2.setOnClickListener {
            viewModel.fetchNewItsThisForThat()
        }
        binding.deleteAll.setOnClickListener {
            viewModel.deleteAll()
        }
    }
    override fun onStart() {
        super.onStart()
        viewModel.mItsThisForThatLiveData.observe(this, observerThisThat)
        viewModel.mPandaLiveData.observe(this, observerPanda)
    }


    override fun onStop() {
        viewModel.mPandaLiveData.removeObserver(observerPanda)
        viewModel.mItsThisForThatLiveData.removeObserver(observerThisThat)
        super.onStop()
    }

    private fun onItemClick(recyclerViewObject: RecyclerViewObject) {
        when(recyclerViewObject){
            is PandaUi -> {
                with(CustomNotificationManager(this)) {
                    createNotificationCompatBuilder("Panda api", "id de l'élément : " + recyclerViewObject.id)
                }
            }
            is ItsThisForThatUi -> {
                with(CustomNotificationManager(this)) {
                    createNotificationCompatBuilder(
                        "ThisThat api",
                        "id de l'élément : " + recyclerViewObject.id
                    )
                }
            }
        }

    }

    private fun onDeleteClick(recyclerViewObject: RecyclerViewObject) {
        when(recyclerViewObject){
            is PandaUi -> viewModel.deletePanda(recyclerViewObject)
            is ItsThisForThatUi -> viewModel.deleteItsThisForThat(recyclerViewObject)
        }
    }

    private fun generateRecyclerList(liste1: List<RecyclerViewObject>, liste2: List<RecyclerViewObject>): MutableList<RecyclerViewObject>{
        val result = mutableListOf<RecyclerViewObject>()
        val tempList = mutableListOf<RecyclerViewObject>()
        tempList.addAll(liste1)
        tempList.addAll(liste2)
        tempList
        tempList.groupBy {
            SimpleDateFormat("(dd/MM/yyyy) HH", Locale.getDefault()).format(it.timestamp)
        }.forEach{(date, items)->
            var header = viewModel.mHeaderList.stream().filter { header -> header.date.equals(date) }.findFirst().orElse(null)
            if(header == null){
                header = Header(date, System.currentTimeMillis())
                viewModel.mHeaderList.add(header)
            }
            result.add(header)
            result.addAll(items.sortedBy { e -> e.timestamp })
        }

        return result
    }
}

class CustomNotificationManager(private val context: Context) {

    companion object {
        const val TAG = "CustomNotificationManager"
        private const val CHANNEL_ID = "demo_purpose"
        private const val CHANNEL_NAME = "demo purpose channel id"
        private const val CHANNEL_DESCRIPTION = "This channel will received only demo notification"
        private const val REQUEST_CODE = 94043
        private const val NOTIFICATION_ID = 42
    }

    /** Notification manager*/
    private val mNotificationManager: NotificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    init {
        if (!channelNotificationExists()) {
            createNowPlayingChannel()
        }
    }

    fun createNotificationCompatBuilder(apiName: String, info : String) {
        val notificationCompat = NotificationCompat.Builder(context, CHANNEL_ID)
            .setAutoCancel(true)
            .setContentTitle(apiName)
            .setContentText(info)
            .setVisibility(NotificationCompat.VISIBILITY_PUBLIC)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(context)) {
            // notificationId is a unique int for each notification that you must define
            notify(NOTIFICATION_ID, notificationCompat.build())
        }
    }

    /**
     * Check if channel is already created
     */
    private fun channelNotificationExists() = mNotificationManager.getNotificationChannel(CHANNEL_ID) != null

    /**
     * Create the cancel id for notification
     */
    private fun createNowPlayingChannel() {
        val notificationChannel = NotificationChannel(
            CHANNEL_ID,
            CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        ).apply {
            description = CHANNEL_DESCRIPTION
        }
        mNotificationManager.createNotificationChannel(notificationChannel)
    }
}