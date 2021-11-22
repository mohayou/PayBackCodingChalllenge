package com.younes.paybackcodingchallenge.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.younes.paybackcodingchallenge.R
import com.younes.paybackcodingchallenge.databinding.ArtRowBinding
import com.younes.paybackcodingchallenge.model.ImageResult
import javax.inject.Inject

 class ImageRecyclerViewAdapter @Inject constructor(
    val glide : RequestManager
) : RecyclerView.Adapter<ImageRecyclerViewAdapter.ImageViewHolder>() {

    private var onItemClickListener : ((String) -> Unit)? = null

    private val diffUtil = object : DiffUtil.ItemCallback<ImageResult>() {
        override fun areItemsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ImageResult, newItem: ImageResult): Boolean {
            return oldItem == newItem
        }
    }

    private val recyclerListDiffer = AsyncListDiffer(this, diffUtil)

    var images: List<ImageResult>
        get() = recyclerListDiffer.currentList
        set(value) = recyclerListDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ArtRowBinding.inflate(layoutInflater)
        return ImageViewHolder(binding)
    }


    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        holder.bind(images[position])


        holder.itemView.setOnClickListener {

            if(onItemClickListener!=null)
                onItemClickListener?.let { it1 -> it1(images[position].id) }
        }
    }

    class ImageViewHolder(val binding: ArtRowBinding): RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ImageResult) {
            binding.imageResult = data
            binding.executePendingBindings()
        }
    }

        @BindingAdapter("loadImage")
        fun loadImage(ivRowImage: ImageView, url: String) {
            glide.load(url).into(ivRowImage)
        }

    fun setOnItemClickListener(listener : (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun getItemCount(): Int {
        return images.size
    }

}