package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.adapter.ImageSearchResultAdapter
import com.jameermulani.hellounittestingandroid.databinding.FragmentImageSearchBinding
import com.jameermulani.hellounittestingandroid.util.STATUS
import com.jameermulani.hellounittestingandroid.util.showToast
import com.jameermulani.hellounittestingandroid.viewmodel.ArtViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

class ImageSearchFragment @Inject constructor(
    private val imageSearchResultAdapter: ImageSearchResultAdapter
) : Fragment(R.layout.fragment_image_search) {

    private var binding: FragmentImageSearchBinding? = null

    //define viewModel
    private lateinit var artViewModel: ArtViewModel

    /*override fun getViewBinding(): ViewDataBinding? {
        return binding
    }*/

    private var imageSearchJob: Job? = null


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        artViewModel =
            ViewModelProvider(requireActivity())[ArtViewModel::class.java]
        binding = FragmentImageSearchBinding.bind(view)
        imageSearchResultAdapter.setOnSearchedItemClickListener {
            artViewModel.setSelectedImage(it)
            findNavController().popBackStack()
        }
        binding?.run {
            rvSearchImages.apply {
                adapter = imageSearchResultAdapter
                layoutManager = GridLayoutManager(requireContext(), 3)
                hasFixedSize()
            }
            etImageSearch.addTextChangedListener { editable ->
                imageSearchJob?.cancel()
                imageSearchJob = lifecycleScope.launch {
                    delay(1000)
                    editable?.also {
                        if (it.toString().isNotEmpty()) {
                            artViewModel.searchImage(it.toString())
                        }
                    }
                }
            }
        }

        subscribeToObservers()


    }

    private fun subscribeToObservers() {
        artViewModel.resetImageObserver()
        artViewModel.images.observe(viewLifecycleOwner) {
            when (it.status) {
                STATUS.SUCCESS -> {
                    binding?.pbSearchImage?.visibility = View.GONE
                    val urls = it.data?.hits?.map {ir->
                        ir.previewURL
                    }
                    imageSearchResultAdapter.urls = urls ?: listOf()
                }

                STATUS.LOADING -> {
                    binding?.pbSearchImage?.visibility = View.VISIBLE
                }

                STATUS.FAILED -> {
                    binding?.pbSearchImage?.visibility = View.GONE
                    requireContext().showToast(it.error ?: "Error")
                }
            }
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}