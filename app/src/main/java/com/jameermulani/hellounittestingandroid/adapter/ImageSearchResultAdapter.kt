package com.jameermulani.hellounittestingandroid.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.jameermulani.hellounittestingandroid.databinding.SearchedImgItemBinding
import javax.inject.Inject

class ImageSearchResultAdapter @Inject constructor(private val glide: RequestManager) :
    RecyclerView.Adapter<ImageSearchResultAdapter.ImageSearchResultItemViewHolder>() {

        private var onSearchedItemClickListener : ((String)->Unit) ? = null
    //define the diffUtil
    private val diffUtil = object : DiffUtil.ItemCallback<String>(){
        override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
            return oldItem == newItem
        }
    }

    private val listDiffer = AsyncListDiffer(this, diffUtil)
    var urls : List<String>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    fun setOnSearchedItemClickListener(listener : (String)->Unit){
        this.onSearchedItemClickListener = listener
    }

    class ImageSearchResultItemViewHolder(private val binding: SearchedImgItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String, glide: RequestManager, onSearchedItemClickListener : ((String)->Unit)? ) {
            glide.load(url).into(binding.ivSearchImgResult)
            binding.root.setOnClickListener {
                onSearchedItemClickListener?.run {
                    invoke(url)
                }
            }
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int
    ): ImageSearchResultItemViewHolder {
        val binding = SearchedImgItemBinding.inflate(LayoutInflater.from(parent.context))
        return ImageSearchResultItemViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return urls.size
    }

    override fun onBindViewHolder(holder: ImageSearchResultItemViewHolder, position: Int) {
        holder.bind(urls[position], glide, onSearchedItemClickListener)
    }
}