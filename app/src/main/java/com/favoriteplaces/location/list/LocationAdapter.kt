package com.favoriteplaces.location.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.favoriteplaces.R
import kotlinx.android.synthetic.main.location_list_item.view.*

class LocationAdapter :
    ListAdapter<LocationUIModel, LocationAdapter.LocationViewHolder>(diffTool) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
        return LocationViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(location: LocationUIModel) {
            itemView.locationNameText.text = location.name
            itemView.locationTypeText.text = location.type
            itemView.locationRatingText.text = "${location.review}"
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
