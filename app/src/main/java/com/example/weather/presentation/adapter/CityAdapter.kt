package com.example.weather.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.weather.R
import com.example.weather.databinding.CityCardBinding
import com.example.weather.entity.dto.City

/**
 * CityAdapter - adapter for recycler view.
 * Recycler item - card view with city name and country name (or null if it doesn't have country name)
 */
class CityAdapter(
    val context: Context,
    private val cityList: List<City>?,
    private val selects: MutableList<Boolean>?,
    private val onClick: (City) -> Unit
) : RecyclerView.Adapter<CityViewHolder>() {

   private var previousPosition: Int? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val binding = CityCardBinding.inflate(LayoutInflater.from(parent.context))
        return CityViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {

        val item = cityList?.get(position)
        if (item != null) {
            holder.binding.cityName.text = item.name
            holder.binding.countryName.text = item.country
        }

        holder.binding.root.setOnClickListener {
            if (item != null && selects != null) {
                selects[position] = true

                if (previousPosition != null && previousPosition != position) {
                    previousPosition?.let {
                        selects[it] = false
                        notifyItemChanged(it)
                    }
                }
                previousPosition = holder.absoluteAdapterPosition
                notifyItemChanged(position)
                onClick(item)
            }
        }

        if (selects != null) {
            if (selects[position])
                holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primaryLightColor))
            else
                holder.binding.cardView.setCardBackgroundColor(ContextCompat.getColor(context, R.color.primaryDarkColor))
            }

    }

    override fun getItemCount(): Int {
        return cityList?.size ?: 0
    }
}