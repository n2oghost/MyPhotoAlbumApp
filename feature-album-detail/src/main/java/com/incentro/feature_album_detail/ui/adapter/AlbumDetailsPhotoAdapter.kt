package com.incentro.feature_album_detail.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.incentro.feature_album_detail.ui.adapter.holder.AlbumDetailsPhotoViewHolder
import com.incentro.feature_album_detail.ui.model.PhotoUiModel

class AlbumDetailsPhotoAdapter(
    private val dataSet: List<PhotoUiModel>
) : RecyclerView.Adapter<AlbumDetailsPhotoViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): AlbumDetailsPhotoViewHolder {
        return AlbumDetailsPhotoViewHolder.build(viewGroup)
    }

    override fun onBindViewHolder(holder: AlbumDetailsPhotoViewHolder, position: Int) {
        holder.setItem(dataSet[position])
    }

    override fun getItemCount() = dataSet.size

}