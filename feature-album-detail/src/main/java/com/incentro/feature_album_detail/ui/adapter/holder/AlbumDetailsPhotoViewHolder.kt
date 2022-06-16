package com.incentro.feature_album_detail.ui.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incentro.core.util.loadImage
import com.incentro.feature_album_detail.databinding.ItemAlbumDetailsPhotoBinding
import com.incentro.feature_album_detail.ui.model.PhotoUiModel

class AlbumDetailsPhotoViewHolder(
    private val binding: ItemAlbumDetailsPhotoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setItem(item: PhotoUiModel) = with(binding) {
        albumDetailsPhotoTitle.text = item.title
        albumDetailsPhotoImage.loadImage(item.url)
    }

    companion object {
        fun build(parent: ViewGroup): AlbumDetailsPhotoViewHolder {
            val binding = ItemAlbumDetailsPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AlbumDetailsPhotoViewHolder(binding)
        }
    }
}