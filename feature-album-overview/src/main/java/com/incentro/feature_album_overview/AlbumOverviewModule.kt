package com.incentro.feature_album_overview

import com.incentro.core.network.networkModule
import com.incentro.core_db.dataBaseModule
import com.incentro.feature_album_overview.data.repository.AlbumOverviewRepository
import com.incentro.feature_album_overview.data.service.AlbumOverviewService
import com.incentro.feature_album_overview.domain.GetLocalAlbumsUseCase
import com.incentro.feature_album_overview.domain.LoadLatestAlbumsUseCase
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val albumOverviewModule = module {

    includes(networkModule, dataBaseModule)

    factory { provideAlbumOverviewService(get()) }
    factory { AlbumOverviewRepository(get(), get(), get()) }
    factory { GetLocalAlbumsUseCase(get()) }
    factory { LoadLatestAlbumsUseCase(get()) }

    viewModel { AlbumOverviewViewModel(get(), get(), get()) }
}

private fun provideAlbumOverviewService(retrofit: Retrofit) : AlbumOverviewService =
    retrofit.create(AlbumOverviewService::class.java)