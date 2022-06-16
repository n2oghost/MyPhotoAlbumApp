package com.incentro.feature_album_overview.ui.model.mapper

import com.incentro.core.util.Mapper
import com.incentro.feature_album_overview.data.model.AlbumDataModel
import com.incentro.feature_album_overview.ui.model.AlbumUiModel
import javax.inject.Inject

class AlbumDataToUiMapper @Inject constructor() : Mapper<AlbumDataModel, AlbumUiModel> {
    override fun map(input: AlbumDataModel): AlbumUiModel = with(input) {
        AlbumUiModel(
            id = id,
            title = title
        )
    }
}