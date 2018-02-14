package com.example.benjohnson.tootootflickerdemo.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import com.example.benjohnson.tootootflickerdemo.databinding.RvItemImageBinding
import com.example.benjohnson.tootootflickerdemo.model.Photo
import com.example.benjohnson.tootootflickerdemo.utils.NetworkingUtils
import com.squareup.picasso.Picasso

/**
 * Adapter to display the images returned from the search
 */
class ImageResultAdapter(private var items: List<Photo>, private var listener: OnItemClickListener)
    : RecyclerView.Adapter<ImageResultAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent?.context)
        val binding = RvItemImageBinding.inflate(layoutInflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(items[position], listener)

    override fun getItemCount(): Int = items.size

    interface OnItemClickListener {
        fun onItemClick(item: Photo)
    }

    class ViewHolder(private var binding: RvItemImageBinding) :
            RecyclerView.ViewHolder(binding.root) {
        lateinit var ivFlickrImage: ImageView
        fun bind(image: Photo, listener: OnItemClickListener?) {
            binding.image = image
            if (listener != null) {
//                binding.btnImageDetails.setOnClickListener({ _ -> listener.onItemClick(image) })
            }
            ivFlickrImage = binding.ivFlickrImg
            binding.executePendingBindings()
        }
    }

    override fun onBindViewHolder(holder: ViewHolder?, position: Int, payloads: MutableList<Any>?) {
        super.onBindViewHolder(holder, position, payloads)
        val currentItem = items[position]
        //PopulateImageView
        Picasso.with(holder?.itemView?.context)
                .load(NetworkingUtils.generateImageUrl(currentItem.farm.toString(), currentItem.server!!, currentItem.id!!, currentItem.secret!!))
                .into(holder!!.ivFlickrImage)
    }

    fun replaceData(it: List<Photo>) {
        items = it
        notifyDataSetChanged()
    }

}