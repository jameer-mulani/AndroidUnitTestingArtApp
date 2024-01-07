package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentArtDetailsBinding

class ArtDetailsFragment : BaseFragment(R.layout.fragment_art_details) {

    private lateinit var binding: FragmentArtDetailsBinding
    override fun getViewBinding(): ViewDataBinding? {
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtDetailsBinding.bind(view)
        binding.btSave.setOnClickListener {
            //todo
        }
        binding.ivArtDetail.setOnClickListener {
            findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageSearchFragment())
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }
}