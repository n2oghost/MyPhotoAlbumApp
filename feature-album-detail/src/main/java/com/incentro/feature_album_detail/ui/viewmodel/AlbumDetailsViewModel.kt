package com.incentro.feature_album_detail.ui.viewmodel

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.core_ui.navigation.GlobalDestinations.FeatureAlbumDetails.albumIdArg
import com.incentro.feature_album_detail.R
import com.incentro.feature_album_detail.domain.GetLocalAlbumDetailsUseCase
import com.incentro.feature_album_detail.domain.LoadLatestAlbumDetailsUseCase
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumDetailsViewModel(
    savedStateHandle: SavedStateHandle,
    getLocalAlbumDetailsUseCase: GetLocalAlbumDetailsUseCase,
    loadLatestAlbumDetailsUseCase: LoadLatestAlbumDetailsUseCase,
    dispatcher: CoroutineDispatcher
) : ViewModel() {
    private val albumId: Int = savedStateHandle.get<Int>(albumIdArg) ?:
    throw IllegalArgumentException(NO_ID_EXCEPTION_MESSAGE)

    private val _viewState = MutableStateFlow(AlbumDetailsUiState())
    val viewState = _viewState.asStateFlow()

    init {
        _viewState.update {
            it.copy(loading = true)
        }
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
                    it.copy(loading = false)
                }
            } catch (error: Exception) {
                _viewState.update {
                    it.copy(
                        loading = false,
                        userMessage = R.string.album_details_loading_error
                    )
                }
            }
        }
    }

    fun userMessageShown() {
        _viewState.update {
            it.copy(userMessage = null)
        }
    }

    companion object {
        const val NO_ID_EXCEPTION_MESSAGE = "Missing album ID"
    }
}