package com.jameermulani.hellounittestingandroid.view

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.jameermulani.hellounittestingandroid.adapter.ArtListAdapter
import com.jameermulani.hellounittestingandroid.adapter.ImageSearchResultAdapter
import javax.inject.Inject

class ArtFragmentFactory @Inject constructor(
    private val glide: RequestManager,
    private val artListAdapter: ArtListAdapter,
    private val imageSearchResultAdapter: ImageSearchResultAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ArtFragment::class.java.name -> ArtFragment(artListAdapter)
            ArtDetailsFragment::class.java.name -> ArtDetailsFragment(glide)
            ImageSearchFragment::class.java.name -> ImageSearchFragment(imageSearchResultAdapter)
            else -> super.instantiate(classLoader, className)
        }
    }

}