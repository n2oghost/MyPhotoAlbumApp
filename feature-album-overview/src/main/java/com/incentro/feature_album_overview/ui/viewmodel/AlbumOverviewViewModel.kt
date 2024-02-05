package com.incentro.feature_album_overview.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.feature_album_overview.domain.GetLocalAlbumsUseCase
import com.incentro.feature_album_overview.domain.LoadLatestAlbumsUseCase
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiLoadingState
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AlbumOverviewViewModel(
    loadLatestAlbumsUseCase: LoadLatestAlbumsUseCase,
    getLocalAlbumsUseCase: GetLocalAlbumsUseCase,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewState = MutableStateFlow(AlbumOverviewUiState())
    val viewState = _viewState.asStateFlow()

    init {
        viewModelScope.launch(dispatcher) {
            getLocalAlbumsUseCase().collect { albums ->
                _viewState.update {
                    it.copy(albums = albums)
                }
            }
        }
        viewModelScope.launch(dispatcher) {
            try {
                loadLatestAlbumsUseCase()
                _viewState.update {
                    it.copy(loadingState = AlbumOverviewUiLoadingState.Success)
                }
            } catch (error: Exception) {
                _viewState.update {
                    it.copy(loadingState = AlbumOverviewUiLoadingState.Error(error.message))
                }
            }
        }
    }
}