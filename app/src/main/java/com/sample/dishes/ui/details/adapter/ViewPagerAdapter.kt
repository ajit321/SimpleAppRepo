package com.sample.dishes.ui.details.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.dishes.databinding.ItemPageBinding

class ViewPagerAdapter(
    private val layoutInflater: LayoutInflater,
    private val moreImages: List<String>
) :
    RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {
    var context: Context = layoutInflater.context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewPagerHolder(
        ItemPageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.setViewBind(moreImages[position], context)
    }

    override fun getItemCount(): Int = moreImages.size

    class ViewPagerHolder(private val binding: ItemPageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun setViewBind(dataPoints: String, context: Context) {
            Glide.with(context).load(dataPoints)
                .into(binding.mediaImage)
        }
    }
}