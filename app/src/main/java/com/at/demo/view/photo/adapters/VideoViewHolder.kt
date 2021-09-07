package com.at.demo.view.photo.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.at.demo.R
import com.at.demo.databinding.ItemPhotoListBinding
import com.at.demo.model.bean.PhotoItem
import com.at.demo.view.extensions.loadImage

class VideoViewHolder(private val itemPhotoListBinding: ItemPhotoListBinding) :
    RecyclerView.ViewHolder(itemPhotoListBinding.root) {

    fun bind(item: PhotoItem, clickListener: (PhotoItem) -> Unit) {
        itemView.setOnClickListener { clickListener(item) }

        val context = itemPhotoListBinding.root.context

        itemPhotoListBinding.ivThumbnail.loadImage(context, item.imagePath)

    }

    companion object {
        fun create(parent: ViewGroup): VideoViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding =
                DataBindingUtil.inflate<ItemPhotoListBinding>(
                    layoutInflater,
                    R.layout.item_photo_list,
                    parent,
                    false
                )
            return VideoViewHolder(binding)

        }
    }
}
