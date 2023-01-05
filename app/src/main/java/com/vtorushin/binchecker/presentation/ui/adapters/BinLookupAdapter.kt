package com.vtorushin.binchecker.presentation.ui.adapters

import android.content.Intent
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.vtorushin.data.db.dtos.BinLookupEntity

class BinLookupAdapter(val onIntent: (Intent) -> Unit) :
    PagingDataAdapter<BinLookupEntity, BinLookupViewHolder>(BinLookupEntityDiffUtil()) {
    override fun onBindViewHolder(holder: BinLookupViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = BinLookupViewHolder(parent, onIntent)
}

class BinLookupEntityDiffUtil : DiffUtil.ItemCallback<BinLookupEntity>() {
    override fun areItemsTheSame(oldItem: BinLookupEntity, newItem: BinLookupEntity) =
        oldItem.uid == newItem.uid

    override fun areContentsTheSame(oldItem: BinLookupEntity, newItem: BinLookupEntity) =
        oldItem.uid == newItem.uid
}