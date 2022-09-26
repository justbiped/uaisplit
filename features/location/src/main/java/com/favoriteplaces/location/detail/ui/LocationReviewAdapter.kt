package com.favoriteplaces.location.detail.ui

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
import com.favoriteplaces.location.R
import com.favoriteplaces.location.databinding.LocationDetailReviewListItemBinding
import com.favoriteplaces.location.detail.data.ui.LocationReviewUIModel
import kotlin.random.Random

internal class LocationReviewAdapter :
    ListAdapter<LocationReviewUIModel, LocationReviewAdapter.LocationReviewViewHolder>(diffTool) {

    private lateinit var binding: LocationDetailReviewListItemBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationReviewViewHolder {
        binding = LocationDetailReviewListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return LocationReviewViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: LocationReviewViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class LocationReviewViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(locationReview: LocationReviewUIModel) {
            binding.locationReviewRatingBar.rating = locationReview.rating.toFloat()
            binding.locationReviewTitleText.text = locationReview.title
            binding.locationReviewCommentText.text = locationReview.comment
            binding.locationReviewAuthorText.text = locationReview.author


            binding.locationReviewAuthorImage.load("") {
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
