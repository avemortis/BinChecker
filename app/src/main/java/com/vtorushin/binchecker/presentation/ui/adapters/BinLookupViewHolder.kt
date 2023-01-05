package com.vtorushin.binchecker.presentation.ui.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vtorushin.binchecker.R
import com.vtorushin.binchecker.databinding.ViewholderBinLookupBinding
import com.vtorushin.data.db.dtos.BinLookupEntity

class BinLookupViewHolder(
    parent: ViewGroup,
    val onIntent: (Intent) -> Unit
) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.viewholder_bin_lookup, parent, false)
) {
    private val binding = ViewholderBinLookupBinding.bind(itemView)

    fun bind(entity: BinLookupEntity?) {
        binding.viewholderBinLookupSheme.text = entity?.scheme
        binding.viewHolderBinLookupType.text = entity?.type
        binding.viewholderBinLookupBrand.text = entity?.brand
        binding.viewholderBinLookupCountry.text = entity?.country.toString()
        if (entity?.prepaid == null) binding.viewholderBinLookupPrepaid.visibility =
            View.GONE else binding.viewholderBinLookupPrepaid.text = entity.prepaid.toString()
        if (entity?.bank == null) {
            binding.viewholderBinLookupBank.visibility = View.GONE
            binding.viewholderBinLookupUrlButton.visibility = View.GONE
            binding.viewholderBinLookupNumberButton.visibility = View.GONE
        } else binding.viewholderBinLookupBank.text = entity.bank.toString()
        if (entity?.number == null) binding.viewholderBinLookupNumber.visibility =
            View.GONE else binding.viewholderBinLookupNumber.text = entity.number.toString()
        clickListeners(entity)
    }

    private fun clickListeners(entity: BinLookupEntity?) {
        binding.viewholderBinLookupGpsButton.setOnClickListener {
            val gmmIntentUri = Uri.parse("google.streetview:cbll=${entity?.country?.latitude},${entity?.country?.longitude}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
            mapIntent.setPackage("com.google.android.apps.maps");
            onIntent(mapIntent)
        }
        if (entity?.bank != null) {
            binding.viewholderBinLookupUrlButton.setOnClickListener {
                val url = "http://${entity.bank?.url}"
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                onIntent(intent)
            }
            binding.viewholderBinLookupNumberButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${entity.bank?.phone}"))
                onIntent(intent)
            }
        }
    }
}