package com.incentro.feature_album_overview.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.incentro.feature_album_overview.domain.GetAlbumsUseCase
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

@HiltViewModel
class AlbumOverviewViewModel @Inject constructor(
    getAlbumsUseCase: GetAlbumsUseCase,
    dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _viewStateLiveData = MutableLiveData<AlbumOverviewUiState>()
    val viewStateLiveData: LiveData<AlbumOverviewUiState> = _viewStateLiveData

    init {
        _viewStateLiveData.value = AlbumOverviewUiState.Loading
        viewModelScope.launch(dispatcher) {
            try {
                getAlbumsUseCase().collect { albums ->
                    _viewStateLiveData.postValue(
                        if (albums.isEmpty())
                            AlbumOverviewUiState.Empty
                        else
                            AlbumOverviewUiState.Success(albums)
                    )
                }

            } catch (error: Exception) {
                _viewStateLiveData.postValue(
                    AlbumOverviewUiState.Error(error.message)
                )
            }
        }
    }
}