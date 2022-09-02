package com.incentro.feature_album_detail.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.feature_album_detail.domain.GetLocalAlbumDetailsUseCase
import com.incentro.feature_album_detail.domain.LoadLatestAlbumDetailsUseCase
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiLoadingState
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getLocalAlbumDetailsUseCase: GetLocalAlbumDetailsUseCase,
    loadLatestAlbumDetailsUseCase: LoadLatestAlbumDetailsUseCase,
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val albumId: Int = savedStateHandle.get<Int>("id") ?:
    throw IllegalArgumentException("Missing album ID")

    private val _viewState = MutableStateFlow(AlbumDetailsUiState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            getLocalAlbumDetailsUseCase(albumId).collect { album ->
                _viewState.update {
                    it.copy(photos = album)
                }
            }
        }
        viewModelScope.launch(dispatcher) {
            try {
                loadLatestAlbumDetailsUseCase(albumId)
                _viewState.update {
                    it.copy(loadingState = AlbumDetailsUiLoadingState.Success)
                }
            } catch (error: Exception) {
                _viewState.update {
                    it.copy(loadingState = AlbumDetailsUiLoadingState.Error(error.message))
                }
            }
        }
    }
}