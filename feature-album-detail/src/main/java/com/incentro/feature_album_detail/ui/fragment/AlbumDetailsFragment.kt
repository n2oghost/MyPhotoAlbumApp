package com.incentro.feature_album_detail.ui.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.incentro.feature_album_detail.databinding.FragmentAlbumDetailsBinding
import com.incentro.feature_album_detail.ui.state.AlbumDetailsUiState
import com.incentro.feature_album_detail.ui.viewmodel.AlbumDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : Fragment() {

    private val viewModel: AlbumDetailsViewModel by viewModels()
    private var _binding: FragmentAlbumDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlbumDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.viewStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AlbumDetailsUiState.Success -> {

                }
                is AlbumDetailsUiState.Error -> {
                    Toast.makeText(activity, it.errorMessage, Toast.LENGTH_LONG).show()
                }
                else -> {}
            }
        }

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(
                Uri.parse(
                    getString(com.incentro.core.R.string.deeplink_album_overview)
                )
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}