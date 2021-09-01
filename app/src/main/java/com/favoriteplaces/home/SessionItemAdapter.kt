package com.favoriteplaces.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.favoriteplaces.R
import com.favoriteplaces.databinding.HomeSessionListItemBinding

class SessionItemAdapter :
    ListAdapter<SessionItem, SessionItemAdapter.SessionItemViewHolder>(diffTool) {

    private var onClick: () -> Unit = {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SessionItemViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.home_session_list_item, parent, false)

        return SessionItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: SessionItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun onItemClick(onClick: () -> Unit) {
        this.onClick = onClick
    }

    inner class SessionItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = HomeSessionListItemBinding.bind(itemView)

        fun bind(item: SessionItem) {
            binding.titleText.text = item.title
            binding.bannerImage.load(item.artWork)
            itemView.setOnClickListener { onClick() }
        }
    }

    companion object {
        val diffTool = object : DiffUtil.ItemCallback<SessionItem>() {
            override fun areItemsTheSame(oldItem: SessionItem, newItem: SessionItem): Boolean {
                return oldItem === newItem
            }

            override fun areContentsTheSame(oldItem: SessionItem, newItem: SessionItem): Boolean {
                return oldItem.title == newItem.title
            }
        }
    }
}