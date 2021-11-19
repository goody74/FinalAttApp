package com.og.randomizer
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class BannerViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(banner: Banner){
        val bannerImageView: ImageView=itemView.findViewById(R.id.banner_image_view)
        val bannerDescriptionsTextView:TextView=itemView.findViewById(R.id.description_text_view)

        bannerImageView.setImageDrawable(itemView.context.getDrawable(banner.imageRes))
        bannerDescriptionsTextView.text=banner.description
    }

}