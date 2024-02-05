package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.repository.AlbumOverviewRepository

class LoadLatestAlbumsUseCase(
    private val albumOverviewRepository: AlbumOverviewRepository
) {
    suspend operator fun invoke() {
        albumOverviewRepository.fetchLatestAlbums()
    }
}