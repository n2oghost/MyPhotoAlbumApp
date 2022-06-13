package com.incentro.feature_album_overview

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.incentro.feature_album_overview.databinding.FragmentAlbumOverviewBinding

class AlbumOverviewFragment : Fragment() {

    private var _binding: FragmentAlbumOverviewBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAlbumOverviewBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(Uri.parse("incentro://album-details/"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}