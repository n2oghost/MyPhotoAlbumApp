package com.incentro.feature_album_overview.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.incentro.feature_album_overview.databinding.FragmentAlbumOverviewBinding
import com.incentro.feature_album_overview.ui.adapter.AlbumOverviewAlbumAdapter
import com.incentro.feature_album_overview.ui.state.AlbumOverviewUiState
import com.incentro.feature_album_overview.ui.viewmodel.AlbumOverviewViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumOverviewFragment : Fragment() {

    private val viewModel: AlbumOverviewViewModel by viewModels()
    private lateinit var binding: FragmentAlbumOverviewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAlbumOverviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AlbumOverviewUiState.Loading -> {
                    binding.albumOverviewProgressBar.visibility = View.VISIBLE
                }
                is AlbumOverviewUiState.Success -> {
                    binding.albumOverviewProgressBar.visibility = View.GONE
                    binding.albumOverviewList.adapter = AlbumOverviewAlbumAdapter(
                        it.albums,
                        ::onAlbumClick
                    )
                }
                is AlbumOverviewUiState.Error -> {
                    binding.albumOverviewProgressBar.visibility = View.GONE
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
                is AlbumOverviewUiState.Empty -> {
                    binding.albumOverviewProgressBar.visibility = View.GONE
                    Toast.makeText(activity, MESSAGE_EMPTY, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }
    }

    private fun onAlbumClick(id: Int) {
        findNavController()
            .navigate(
                getString(com.incentro.core.R.string.deeplink_album_details)
                    .replace("{id}", id.toString())
                    .toUri()
            )
    }

    companion object {
        private const val MESSAGE_EMPTY = "No albums found."
    }
}