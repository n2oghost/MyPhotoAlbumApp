package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import javax.inject.Inject

class LoadLatestAlbumsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    suspend operator fun invoke() {
        albumsRepository.fetchLatestAlbums()
    }
}