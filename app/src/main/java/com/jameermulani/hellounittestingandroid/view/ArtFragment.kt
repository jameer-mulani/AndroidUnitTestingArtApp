package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentArtsListBinding

class ArtFragment : Fragment(R.layout.fragment_arts_list) {

    private var binding: FragmentArtsListBinding? = null
    /*override fun getViewBinding(): ViewDataBinding? {
        return binding
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentArtsListBinding.bind(view)
        binding?.fabArts?.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}