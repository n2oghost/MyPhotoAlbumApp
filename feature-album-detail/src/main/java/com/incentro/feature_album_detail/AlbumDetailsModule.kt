package com.incentro.feature_album_detail

import com.incentro.core.network.networkModule
import com.incentro.core_db.dataBaseModule
import com.incentro.feature_album_detail.data.repository.AlbumDetailsRepository
import com.incentro.feature_album_detail.data.service.AlbumDetailsService
import com.incentro.feature_album_detail.domain.GetLocalAlbumDetailsUseCase
import com.incentro.feature_album_detail.domain.LoadLatestAlbumDetailsUseCase
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit

val albumDetailsModule = module {

    includes(networkModule, dataBaseModule)

    factory { provideAlbumDetailsService(get()) }
    factory { AlbumDetailsRepository(get(), get(), get()) }
    factory { GetLocalAlbumDetailsUseCase(get()) }
    factory { LoadLatestAlbumDetailsUseCase(get()) }

    viewModel { AlbumDetailsViewModel(get(), get(), get(), get()) }
}

private fun provideAlbumDetailsService(retrofit: Retrofit) : AlbumDetailsService =
    retrofit.create(AlbumDetailsService::class.java)