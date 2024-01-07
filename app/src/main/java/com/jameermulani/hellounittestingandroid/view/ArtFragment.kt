package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.navigation.fragment.findNavController
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentArtsListBinding

class ArtFragment : BaseFragment(R.layout.fragment_arts_list) {

    private lateinit var binding: FragmentArtsListBinding
    override fun getViewBinding(): ViewDataBinding {
        return binding
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtsListBinding.bind(view)
        binding.fabArts.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }

}