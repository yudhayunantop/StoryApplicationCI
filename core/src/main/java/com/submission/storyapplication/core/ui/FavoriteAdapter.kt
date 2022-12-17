package com.submission.storyapplication.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.databinding.ItemStoryBinding
import com.submission.storyapplication.core.domain.model.Stories

class FavoriteAdapter: RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {
    var listStories = listOf<Stories>()
    lateinit var onClickListener : OnClickListener

    @JvmName("setListStories1")
    fun setListStories(list: List<Stories>) {
        this.listStories = list
    }

    @JvmName("setOnClickListener1")
    fun setOnClickListener(onClickListener: OnClickListener) {
        this.onClickListener = onClickListener
    }


    class ViewHolder(itemView: ItemStoryBinding) : RecyclerView.ViewHolder(itemView.root) {
         var binding: ItemStoryBinding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = listStories[position]
        with(holder){
            Glide.with(itemView.context)
                .load(current.photoUrl)
                .apply(RequestOptions().override(55, 55))
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = current.name
            binding.tvItemDesc.text = current.description

            itemView.setOnClickListener {
                onClickListener.onItemClick(current)
            }
        }
    }

    override fun getItemCount(): Int = listStories.size

    interface OnClickListener{
        fun onItemClick(stories: Stories)
    }
}