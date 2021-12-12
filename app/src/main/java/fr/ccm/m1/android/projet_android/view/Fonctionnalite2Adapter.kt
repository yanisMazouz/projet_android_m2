package fr.ccm.m1.android.projet_android.view

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.ccm.m1.android.projet_android.databinding.ItemHeaderBinding
import fr.ccm.m1.android.projet_android.databinding.ItemPandaBinding
import fr.ccm.m1.android.projet_android.databinding.ItemThisThatBinding
import fr.ccm.m1.android.projet_android.model.Header
import fr.ccm.m1.android.projet_android.model.ItsThisForThatUi
import fr.ccm.m1.android.projet_android.model.PandaUi
import fr.ccm.m1.android.projet_android.model.RecyclerViewObject

enum class MyItemType(val type: Int) {
    PANDA(0),
    THIS_THAT(1),
    HEADER(2)
}


val diffUtils = object : DiffUtil.ItemCallback<RecyclerViewObject>() {
    override fun areItemsTheSame(oldItem: RecyclerViewObject, newItem: RecyclerViewObject): Boolean {
        return oldItem == newItem
    }
    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: RecyclerViewObject, newItem: RecyclerViewObject): Boolean {
        return oldItem == newItem
    }
}

class PandaViewHolder(
    val binding: ItemPandaBinding,
    onItemClick: ( recyclerViewObject: RecyclerViewObject) -> Unit,
    onDeleteClick: ( recyclerViewObject: RecyclerViewObject) -> Unit

) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: PandaUi
    init {
        binding.root.setOnClickListener {
            onItemClick(ui)
        }
        binding.deleteButton.setOnClickListener{
            onDeleteClick(ui)
        }
    }
    fun bind(pandaUi: PandaUi) {
        ui = pandaUi
        Glide.with(itemView.context)
            .load(pandaUi.image)
            .into(binding.itemPandaIcon)


        binding.itemPandaPhrase.text = pandaUi.fact
    }
}

class ItsThisForViewHolder(
    val binding: ItemThisThatBinding,
    onItemClick: ( recyclerViewObject: RecyclerViewObject) -> Unit,
    onDeleteClick: ( recyclerViewObject: RecyclerViewObject) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: ItsThisForThatUi
    init {
        binding.root.setOnClickListener {
            onItemClick(ui)
        }
        binding.deleteButton.setOnClickListener{
            onDeleteClick(ui)
        }
    }
    fun bind(itsThisForThatUi: ItsThisForThatUi) {
        ui = itsThisForThatUi
        binding.itsThis.text = itsThisForThatUi.itsThis
        binding.forThat.text = itsThisForThatUi.forThat
    }
}

class HeaderViewHolder(
    val binding: ItemHeaderBinding
) : RecyclerView.ViewHolder(binding.root) {

    private lateinit var ui: Header

    fun bind(header: Header) {
        ui = header
        binding.itemRecyclerViewHeader.text = header.date + "H"
    }
}

class Fonctionnalite2Adapter( private val onItemClick: (recyclerViewObject: RecyclerViewObject) -> Unit,
                              private val onDeleteClick: (recyclerViewObject: RecyclerViewObject) -> Unit) : ListAdapter<RecyclerViewObject, RecyclerView.ViewHolder>(diffUtils) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (viewType) {


            MyItemType.PANDA.type -> {
                PandaViewHolder(
                    ItemPandaBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick,onDeleteClick
                )
            }

            MyItemType.THIS_THAT.type -> {
                ItsThisForViewHolder(
                    ItemThisThatBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    ), onItemClick,onDeleteClick
                )
            }


            MyItemType.HEADER.type -> {
                HeaderViewHolder(
                    ItemHeaderBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                )
            }
            else -> throw RuntimeException("Wrong view type received $viewType")
        }





    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder.itemViewType) {
            MyItemType.PANDA.type -> (holder as PandaViewHolder).bind(getItem(position) as PandaUi)

            MyItemType.THIS_THAT.type -> (holder as ItsThisForViewHolder).bind(getItem(position) as ItsThisForThatUi)

            MyItemType.HEADER.type -> (holder as HeaderViewHolder).bind(getItem(position) as Header)


            else -> throw RuntimeException("Wrong view type received ${holder.itemView}")
        }


    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is PandaUi -> MyItemType.PANDA.type
            is ItsThisForThatUi -> MyItemType.THIS_THAT.type
            else -> MyItemType.HEADER.type
        }
    }
}


