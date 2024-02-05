package com.incentro.feature_album_overview.domain

import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.repository.AlbumOverviewRepository
import kotlinx.coroutines.flow.Flow

class GetLocalAlbumsUseCase(
    private val albumOverviewRepository: AlbumOverviewRepository
) {
    suspend operator fun invoke() : Flow<List<Album>> {
        return albumOverviewRepository.albums
    }
}