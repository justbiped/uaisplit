package com.favoriteplaces.location.detail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.favoriteplaces.R
import kotlinx.android.synthetic.main.location_detail_review_list_item.view.*
import kotlin.random.Random

class LocationReviewAdapter :
    ListAdapter<LocationReviewUIModel, LocationReviewAdapter.LocationReviewViewHolder>(diffTool) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationReviewViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.location_detail_review_list_item, parent, false)
        return LocationReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: LocationReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(locationReview: LocationReviewUIModel) {
            itemView.locationReviewRatingBar.rating = locationReview.rating.toFloat()
            itemView.locationReviewTitleText.text = locationReview.title
            itemView.locationReviewCommentText.text = locationReview.comment
            itemView.locationReviewAuthorText.text = locationReview.author
        }
    }

    private fun getPlaceHolder() = when (Random.nextInt(0, 4)) {
        0 -> R.color.turquoise_100
        1 -> R.color.red_100
        else -> R.color.yellow_200
    }

    companion object {
        val diffTool = object : DiffUtil.ItemCallback<LocationReviewUIModel>() {
            override fun areItemsTheSame(
                oldItem: LocationReviewUIModel,
                newItem: LocationReviewUIModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: LocationReviewUIModel,
                newItem: LocationReviewUIModel
            ): Boolean {
                return oldItem.comment == newItem.comment
            }
        }
    }
}
