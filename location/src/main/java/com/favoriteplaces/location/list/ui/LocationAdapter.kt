package com.favoriteplaces.location.list.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.favoriteplaces.location.databinding.LocationListItemBinding
import com.favoriteplaces.location.list.data.ui.LocationUIModel

internal class LocationAdapter :
    ListAdapter<LocationUIModel, LocationAdapter.LocationViewHolder>(diffTool) {

    private lateinit var binding: LocationListItemBinding
    private var onItemClicked: (location: LocationUIModel) -> Unit = {}

    fun setItemClickListener(listener: (location: LocationUIModel) -> Unit) {
        onItemClicked = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        binding =
            LocationListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return LocationViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener {
                onItemClicked(getItem(adapterPosition))
            }
        }

        fun bind(location: LocationUIModel) {
            binding.locationNameText.text = location.name
            binding.locationTypeText.text = location.type
            binding.locationRatingText.text = "${location.review}"
            binding.ratingBar.rating = location.review.toFloat()

            binding.locationImageView.load(location.image.url) {
                placeholder(location.image.placeHolderResource)
                error(location.image.placeHolderResource)
            }
        }
    }

    companion object {
        val diffTool = object : DiffUtil.ItemCallback<LocationUIModel>() {
            override fun areItemsTheSame(oldItem: LocationUIModel, newItem: LocationUIModel) =
                oldItem == newItem

            override fun areContentsTheSame(oldItem: LocationUIModel, newItem: LocationUIModel) =
                oldItem.id == newItem.id
        }
    }
}
