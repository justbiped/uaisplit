package com.favoriteplaces.location.detail

import android.content.Context
import android.graphics.drawable.Drawable
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.favoriteplaces.R
import com.favoriteplaces.location.detail.data.ui.LocationReviewUIModel
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


            itemView.locationReviewAuthorImage.load("") {
                val placeholder = getPlaceHolder(itemView.context)

                placeholder(placeholder)
                error(placeholder)
                transformations(CircleCropTransformation())
            }
        }
    }

    private fun getPlaceHolder(context: Context): Drawable {
        val color = when (Random.nextInt(0, 4)) {
            0 -> R.color.turquoise_500
            1 -> R.color.red_500
            else -> R.color.green_500
        }

        return ShapeDrawable(OvalShape()).apply {
            paint.color = ContextCompat.getColor(context, color)
            intrinsicWidth = 1
            intrinsicHeight = 1
        }
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
