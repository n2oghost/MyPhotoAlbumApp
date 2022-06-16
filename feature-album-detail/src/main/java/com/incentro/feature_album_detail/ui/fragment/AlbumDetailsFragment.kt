package com.incentro.feature_album_detail.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.incentro.feature_album_detail.databinding.FragmentAlbumDetailsBinding
import com.incentro.feature_album_detail.ui.adapter.AlbumDetailsPhotoAdapter
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    private val viewModel: AlbumDetailsViewModel by viewModels()
    private lateinit var binding: FragmentAlbumDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AlbumDetailsUiState.Loading -> {
                    binding.albumDetailsProgressBar.visibility = View.VISIBLE
                }
                is AlbumDetailsUiState.Success -> {
                    binding.albumDetailsProgressBar.visibility = View.GONE
                    binding.albumDetailsList.adapter = AlbumDetailsPhotoAdapter(it.photos)
                }
                is AlbumDetailsUiState.Error -> {
                    binding.albumDetailsProgressBar.visibility = View.GONE
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }
}