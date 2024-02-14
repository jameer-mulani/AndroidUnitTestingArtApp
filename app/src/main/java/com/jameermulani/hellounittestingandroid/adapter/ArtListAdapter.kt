package com.jameermulani.hellounittestingandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.jameermulani.hellounittestingandroid.databinding.ArtsListItemBinding
import com.jameermulani.hellounittestingandroid.room.Art
import javax.inject.Inject

class ArtListAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<ArtListAdapter.ArtListItemViewHolder>() {

    class ArtListItemViewHolder(private val binding: ArtsListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(art: Art, glide: RequestManager) {
            binding.apply {
                tvArtName.text = "Name : ${art.name}"
                tvArtistName.text = "Artist : ${art.artistName}"
                tvArtYear.text = "Year : ${art.year}"
                glide.load(art.imageUrl).into(ivArt)
            }
            binding.executePendingBindings()
        }
    }

    //define the diff util
    private val diffUtil = object : DiffUtil.ItemCallback<Art>() {
        override fun areItemsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Art, newItem: Art): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)

    var arts: List<Art>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArtListItemViewHolder {
        val binding = ArtsListItemBinding.inflate(LayoutInflater.from(parent.context))
        return ArtListItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return arts.size
    }

    override fun onBindViewHolder(holder: ArtListItemViewHolder, position: Int) {
        holder.bind(art = arts[position], glide)
    }

}