package com.jameermulani.hellounittestingandroid.view

import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment(
    @LayoutRes private val layoutId: Int
) : Fragment(layoutId) {
    /*        abstract fun getViewBinding() : ViewDataBinding?

        override fun onDestroyView() {
            var viewBinding = getViewBinding()
            viewBinding = null
            super.onDestroyView()
        }*/
}