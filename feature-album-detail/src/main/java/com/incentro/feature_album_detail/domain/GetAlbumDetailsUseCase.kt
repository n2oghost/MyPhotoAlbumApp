package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.model.PhotoDataModel
import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import javax.inject.Inject

class GetAlbumDetailsUseCase @Inject constructor(
    private val albumDetailsRepository: AlbumDetailsRepository
) {
    suspend operator fun invoke(id: Int) : List<PhotoDataModel> {
        return albumDetailsRepository.getAlbumById(id)
    }
}