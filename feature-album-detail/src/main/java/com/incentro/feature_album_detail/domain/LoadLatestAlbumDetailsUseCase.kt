package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import javax.inject.Inject

class LoadLatestAlbumDetailsUseCase @Inject constructor(
    private val albumDetailsRepository: AlbumDetailsRepository
) {
    suspend operator fun invoke(id: Int) {
        albumDetailsRepository.fetchLatestAlbumById(id)
    }
}