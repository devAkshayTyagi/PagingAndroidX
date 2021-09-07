package com.at.demo.view.photo.adapters

import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import com.at.demo.model.bean.PhotoItem

/**
 * Adapter for the list of photos.
 */
class PhotosReposAdapter constructor(val clickListener: (PhotoItem) -> Unit) :
    PagingDataAdapter<PhotoItem, VideoViewHolder>(REPO_COMPARATOR) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideoViewHolder {
        return VideoViewHolder.create(parent)
    }

    override fun onBindViewHolder(holder: VideoViewHolder, position: Int) {
        val repoItem = getItem(position)
        if (repoItem != null) {
            holder.bind(repoItem,clickListener)
        }
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PhotoItem>() {
            override fun areItemsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: PhotoItem, newItem: PhotoItem): Boolean =
                oldItem == newItem
        }
    }
}
