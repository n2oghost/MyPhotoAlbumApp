package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetAlbumsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    suspend operator fun invoke() : Flow<List<Album>> {
        albumsRepository.fetchAlbums()
        return albumsRepository.albums
    }
}