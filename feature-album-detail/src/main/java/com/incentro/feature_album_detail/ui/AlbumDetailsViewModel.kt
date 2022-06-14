package com.incentro.feature_album_detail.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.feature_album_detail.data.AlbumDataModel
import com.incentro.feature_album_detail.data.AlbumDetailsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    albumDetailsRepository: AlbumDetailsRepository
) : ViewModel() {
    private val albumId: Int = savedStateHandle["aid"] ?:
    throw IllegalArgumentException("Missing album ID")

    private val _album = MutableLiveData<AlbumDataModel>()
    val album = _album as LiveData<AlbumDataModel>

    init {
        viewModelScope.launch {
            try {
                _album.value = albumDetailsRepository.getAlbumById(albumId)
            } catch (error: Exception) {

            }
        }
    }
}