package com.incentro.feature_album_overview.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incentro.feature_album_overview.ui.adapter.holder.AlbumOverviewAlbumViewHolder
import com.incentro.feature_album_overview.ui.model.AlbumUiModel

class AlbumOverviewAlbumAdapter(
    private val dataSet: List<AlbumUiModel>,
    private val onAlbumClick: (id: Int) -> Unit
) : RecyclerView.Adapter<AlbumOverviewAlbumViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumOverviewAlbumViewHolder {
        return AlbumOverviewAlbumViewHolder.build(viewGroup, onAlbumClick)
    }

    override fun onBindViewHolder(holder: AlbumOverviewAlbumViewHolder, position: Int) {
        holder.setItem(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}