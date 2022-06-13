package com.incentro.feature_album_detail

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.incentro.feature_album_detail.databinding.FragmentAlbumDetailsBinding

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AlbumDetailsFragment : Fragment() {

    private var _binding: FragmentAlbumDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
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

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(Uri.parse("incentro://album-overview/"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}