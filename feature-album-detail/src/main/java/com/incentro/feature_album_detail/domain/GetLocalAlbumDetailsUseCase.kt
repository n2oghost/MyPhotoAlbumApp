package com.incentro.feature_album_detail.domain

import com.incentro.feature_album_detail.data.model.Photo
import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocalAlbumDetailsUseCase @Inject constructor(
    private val albumDetailsRepository: AlbumDetailsRepository
) {
    suspend operator fun invoke(id: Int) : Flow<List<Photo>> {
        return albumDetailsRepository.getLocalAlbumById(id)
    }
}