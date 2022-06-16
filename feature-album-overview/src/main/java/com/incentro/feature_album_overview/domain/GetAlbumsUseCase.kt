package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.model.AlbumDataModel
import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import javax.inject.Inject

class GetAlbumsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    suspend operator fun invoke() : List<AlbumDataModel> {
        return albumsRepository.getAlbums()
    }
}