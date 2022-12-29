package com.morphylix.android.binlist.presentation.bin_input

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.morphylix.android.binlist.databinding.ItemBinHistoryBinding
import com.morphylix.android.binlist.domain.model.domain.CardBinData
import com.morphylix.android.binlist.util.splitEveryFourDigits

class BinInputAdapter(private val cardList: List<CardBinData>):
    RecyclerView.Adapter<BinInputViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BinInputViewHolder {
        val binding = ItemBinHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BinInputViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BinInputViewHolder, position: Int) {
        holder.bind(cardList[position])
    }

    override fun getItemCount(): Int {
        return cardList.size
    }
}

class BinInputViewHolder(private val binding: ItemBinHistoryBinding): RecyclerView.ViewHolder(binding.root), View.OnClickListener {

    private lateinit var cardBinData: CardBinData

    init {
        itemView.setOnClickListener(this)
    }

    fun bind(card: CardBinData) {
        this.cardBinData = card
        with(binding) {
            cardBinTextView.text = card.bin.splitEveryFourDigits()
        }
    }

    override fun onClick(p0: View) {
        val action = BinInputFragmentDirections.actionBinInputFragmentToBinDetailsFragment(cardBinData.bin)
        p0.findNavController().navigate(action)
    }
}