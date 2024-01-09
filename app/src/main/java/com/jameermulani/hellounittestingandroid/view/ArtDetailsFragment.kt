package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentArtDetailsBinding
import com.jameermulani.hellounittestingandroid.util.STATUS
import com.jameermulani.hellounittestingandroid.util.showToast
import com.jameermulani.hellounittestingandroid.viewmodel.ArtViewModel
import javax.inject.Inject

class ArtDetailsFragment @Inject constructor(private val glide: RequestManager) :
    Fragment(R.layout.fragment_art_details) {

    private var binding: FragmentArtDetailsBinding? = null

    //define the viewModel
    private lateinit var artViewModel: ArtViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artViewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        binding = FragmentArtDetailsBinding.bind(view)
        binding?.btSave?.setOnClickListener {
            artViewModel.makeArt(
                binding!!.etName.text.toString(),
                binding!!.etArtistName.text.toString(),
                binding!!.etYear.text.toString()
            )
        }
        binding?.apply {
            glide.load(artViewModel.selectedImageUrl ?: "").into(ivArtDetail)
            ivArtDetail.setOnClickListener {
                findNavController().navigate(ArtDetailsFragmentDirections.actionArtDetailsFragmentToImageSearchFragment())
            }
        }

        subscribeToObservers()

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun subscribeToObservers() {
        artViewModel.selectedImageUrl.observe(viewLifecycleOwner) {
            binding?.run {
                glide.load(it).into(ivArtDetail)
            }
        }

        artViewModel.insertArtMessage.observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS.SUCCESS -> {
                    requireContext().showToast("Success")
                    findNavController().popBackStack()
                    artViewModel.resetInsertArt()
                }

                STATUS.FAILED -> {
                    requireContext().showToast(it.error ?: "Error")
                }

                STATUS.LOADING -> {
                    // no-ops
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }
}