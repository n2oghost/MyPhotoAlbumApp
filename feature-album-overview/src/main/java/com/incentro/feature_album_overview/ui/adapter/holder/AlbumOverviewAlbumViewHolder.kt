package com.incentro.feature_album_overview.ui.adapter.holder

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incentro.feature_album_overview.databinding.ItemAlbumOverviewAlbumBinding
import com.incentro.feature_album_overview.ui.model.AlbumUiModel

class AlbumOverviewAlbumViewHolder(
    private val binding: ItemAlbumOverviewAlbumBinding,
    private val onAlbumClick: (id: Int) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun setItem(item: AlbumUiModel) = with(binding) {
        albumOverviewAlbumTitle.text = item.title

        binding.root.setOnClickListener {
            onAlbumClick.invoke(item.id)
        }
    }

    companion object {
        fun build(parent: ViewGroup, onAlbumClick: (id: Int) -> Unit): AlbumOverviewAlbumViewHolder {
            val binding = ItemAlbumOverviewAlbumBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
            return AlbumOverviewAlbumViewHolder(binding, onAlbumClick)
        }
    }
}