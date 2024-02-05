package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository

class LoadLatestAlbumDetailsUseCase(
    private val albumDetailsRepository: AlbumDetailsRepository
) {
    suspend operator fun invoke(id: Int) {
        albumDetailsRepository.fetchLatestAlbumById(id)
    }
}