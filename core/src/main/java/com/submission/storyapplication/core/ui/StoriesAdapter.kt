package com.submission.storyapplication.core.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.submission.storyapplication.core.data.local.entity.StoriesEntity
import com.submission.storyapplication.core.databinding.ItemStoryBinding
import com.submission.storyapplication.core.domain.model.Stories

class StoriesAdapter
    : PagingDataAdapter<Stories, StoriesAdapter.ListViewHolder>(DIFF_CALLBACK) {
    lateinit var onClickListener: OnClickListener

    @JvmName("setOnClickListener1")
    fun setOnClickListener(onClickListener: OnClickListener){
        this.onClickListener=onClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val inflater = ItemStoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ListViewHolder(inflater, onClickListener)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
    }

    class ListViewHolder(itemView: ItemStoryBinding, onClickListener: OnClickListener) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemStoryBinding = itemView
        var onClickListener=onClickListener

        fun bind(stories: Stories) {
            Glide.with(itemView.context)
                .load(stories.photoUrl)
                .apply(RequestOptions().override(55, 55))
                .into(binding.ivItemPhoto)
            binding.tvItemName.text = stories.name
            binding.tvItemDesc.text = stories.description

            itemView.setOnClickListener {
                onClickListener.onItemClick(stories)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Stories>() {
            override fun areItemsTheSame(
                oldItem: Stories,
                newItem: Stories
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: Stories,
                newItem: Stories
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    interface OnClickListener{
        fun onItemClick(stories: Stories)
    }
}