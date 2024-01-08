package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentImageSearchBinding

class ImageSearchFragment : Fragment(R.layout.fragment_image_search) {

    private var binding: FragmentImageSearchBinding? = null

    /*override fun getViewBinding(): ViewDataBinding? {
        return binding
    }*/


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageSearchBinding.bind(view)
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}