package com.incentro.feature_album_overview.domain

import android.util.Log
import com.incentro.feature_album_overview.data.model.Album
import com.incentro.feature_album_overview.data.repository.AlbumsRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.Flow

class GetLocalAlbumsUseCase @Inject constructor(
    private val albumsRepository: AlbumsRepository
) {
    suspend operator fun invoke() : Flow<List<Album>> {
        return albumsRepository.albums
    }
}