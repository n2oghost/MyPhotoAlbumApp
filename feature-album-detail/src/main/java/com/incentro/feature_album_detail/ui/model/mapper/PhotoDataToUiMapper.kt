package com.incentro.feature_album_detail.ui.model.mapper

import com.incentro.core.util.Mapper
import com.incentro.feature_album_detail.data.model.PhotoDataModel
import com.incentro.feature_album_detail.ui.model.PhotoUiModel
import javax.inject.Inject

class PhotoDataToUiMapper @Inject constructor() : Mapper<PhotoDataModel, PhotoUiModel> {
    override fun map(input: PhotoDataModel): PhotoUiModel = with(input) {
        PhotoUiModel(
            id = id,
            title = title,
            url = url
        )
    }
}