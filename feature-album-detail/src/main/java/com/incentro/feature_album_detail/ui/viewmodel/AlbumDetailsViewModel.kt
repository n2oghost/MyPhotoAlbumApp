package com.incentro.feature_album_detail.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.feature_album_detail.domain.GetAlbumDetailsUseCase
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.model.mapper.PhotoDataToUiMapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getAlbumDetailsUseCase: GetAlbumDetailsUseCase,
    photoDataToUiMapper: PhotoDataToUiMapper,
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val albumId: Int = savedStateHandle["id"] ?:
    throw IllegalArgumentException("Missing album ID")

    private val _viewStateLiveData = MutableLiveData<AlbumDetailsUiState>()
    val viewStateLiveData: LiveData<AlbumDetailsUiState> = _viewStateLiveData

    init {
        _viewStateLiveData.value = AlbumDetailsUiState.Loading
        viewModelScope.launch(dispatcher) {
            try {
                val albumDetails = getAlbumDetailsUseCase(albumId)
                    .map(photoDataToUiMapper::map)
                _viewStateLiveData.postValue(
                    if (albumDetails.isEmpty())
                        AlbumDetailsUiState.Empty
                    else
                        AlbumDetailsUiState.Success(albumDetails)
                )
            } catch (error: Exception) {
                _viewStateLiveData.postValue(
                    AlbumDetailsUiState.Error(error.message)
                )
            }
        }
    }
}