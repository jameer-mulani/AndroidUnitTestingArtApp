package com.jameermulani.hellounittestingandroid.view

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.jameermulani.hellounittestingandroid.R
import com.jameermulani.hellounittestingandroid.adapter.ArtListAdapter
import com.jameermulani.hellounittestingandroid.databinding.FragmentArtsListBinding
import com.jameermulani.hellounittestingandroid.viewmodel.ArtViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ArtFragment @Inject constructor(
    private val artListAdapter: ArtListAdapter
) : Fragment(R.layout.fragment_arts_list) {

    private var binding: FragmentArtsListBinding? = null
    /*override fun getViewBinding(): ViewDataBinding? {
        return binding
    }*/

    //define the viewmodel
    private lateinit var artViewModel: ArtViewModel

    //define the swipe callback
    private val swipeCallback = object : ItemTouchHelper.SimpleCallback(0,
        ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.layoutPosition
            val selectedArt = artListAdapter.arts[position]
            artViewModel.deleteArt(selectedArt)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        artViewModel = ViewModelProvider(requireActivity())[ArtViewModel::class.java]

        binding = FragmentArtsListBinding.bind(view)

        binding?.let {
            it.rvArts.adapter = artListAdapter
            it.rvArts.layoutManager = LinearLayoutManager(requireContext())
        }

        ItemTouchHelper(swipeCallback).attachToRecyclerView(binding!!.rvArts)

        binding?.fabArts?.setOnClickListener {
            findNavController().navigate(ArtFragmentDirections.actionArtFragmentToArtDetailsFragment())
        }

        subscribeToObservers()
    }

    private fun subscribeToObservers(){
        artViewModel.artsList.observe(viewLifecycleOwner) {
            artListAdapter.arts = it
        }
    }

    override fun onDestroyView() {
        binding = null
        super.onDestroyView()
    }

}