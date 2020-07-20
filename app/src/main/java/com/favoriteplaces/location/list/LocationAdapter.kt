package com.favoriteplaces.location.list

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.favoriteplaces.R
import com.favoriteplaces.location.list.data.LocationUIModel
import kotlinx.android.synthetic.main.location_list_item.view.*
import kotlin.random.Random

class LocationAdapter :
    ListAdapter<LocationUIModel, LocationAdapter.LocationViewHolder>(diffTool) {

    private var onItemClicked: (location: LocationUIModel) -> Unit = {}

    fun setItemClickListener(listener: (location: LocationUIModel) -> Unit) {
        onItemClicked = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.location_list_item, parent, false)
        return LocationViewHolder(view)
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
            itemView.locationNameText.text = location.name
            itemView.locationTypeText.text = location.type
            itemView.locationImageView.load(location.image) { placeholder(getPlaceHolder()) }
            itemView.locationRatingText.text = "${location.review}"
            itemView.ratingBar.rating = location.review.toFloat()
        }
    }

    private fun getPlaceHolder() = when (Random.nextInt(0, 4)) {
        0 -> R.color.turquoise_100
        1 -> R.color.red_100
        else -> R.color.yellow_200
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
