package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import androidx.databinding.ViewDataBinding
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.databinding.FragmentImageSearchBinding

class ImageSearchFragment : BaseFragment(R.layout.fragment_image_search) {

    private lateinit var binding: FragmentImageSearchBinding

    override fun getViewBinding(): ViewDataBinding? {
        return binding
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImageSearchBinding.bind(view)
    }

}