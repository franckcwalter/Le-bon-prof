package com.devid_academy.projetfinal.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.devid_academy.projetfinal.R
import com.devid_academy.projetfinal.databinding.ItemAdBinding
import com.devid_academy.projetfinal.network.AdDto
import com.squareup.picasso.Picasso
import java.text.SimpleDateFormat
import java.util.Locale

class AdAdapter : ListAdapter<AdDto, AdViewHolder>(MyDiffUtil()){

    var onItemClick : ((Long) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdViewHolder {
        return LayoutInflater
                .from(parent.context)
                .inflate(R.layout.item_ad, parent, false)
                    .run {
                        AdViewHolder(this)
                    }
    }

    override fun onBindViewHolder(holder: AdViewHolder, position: Int) {

        getItem(position).let { adDto ->
            with(holder.binding) {

                tvItemAdTitle.text      = adDto.title
                tvItemAdPrice.text      = adDto.price
                tvItemAdTeacherName.text = adDto.firstName
                tvItemLocation.text     = adDto.location

                Picasso.get()
                    .load(adDto.photo.ifEmpty { "noImg" })
                    .placeholder(R.drawable.logo_small)
                    .error(R.drawable.logo_small)
                    .into(ivItemPhoto)

                root.setOnClickListener{
                    onItemClick?.invoke(adDto.id)
                }

                ivItemFavStar.isVisible = adDto.isFav == 1

            }
        }
    }
}

class AdViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
    val binding = ItemAdBinding.bind(itemView)
}

class MyDiffUtil : DiffUtil.ItemCallback<AdDto>(){
    override fun areItemsTheSame(oldItem: AdDto, newItem: AdDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: AdDto, newItem: AdDto): Boolean {
        return oldItem == newItem
    }
}