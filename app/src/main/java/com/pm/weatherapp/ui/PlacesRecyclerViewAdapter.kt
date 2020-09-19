package com.pm.weatherapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.pm.weatherapp.R
import com.pm.weatherapp.model.Forecast
import kotlinx.android.synthetic.main.item_recyclerview.view.*

class PlacesRecyclerViewAdapter(
    private var selectedPos: Int,
    private val item: Forecast,
    private val context: Context,
    val clickListener: (Int) -> Unit,
): RecyclerView.Adapter<PlacesRecyclerViewAdapter.CustomViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): CustomViewHolder {
        val rootView: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_recyclerview, parent, false)

        return CustomViewHolder(rootView)
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int,
    ) {

        val itemDay = item.day.places?.get(position)
        val itemNight = item.night.places?.get(position)

        holder.apply {

            nameTextView.text = itemDay?.name
            tempMaxTextView.text = itemDay?.tempMax.toString()
            tempMinTextView.text = itemNight?.tempMin.toString()

            if (selectedPos == position) {
                row.setBackgroundColor(ContextCompat.getColor(context, R.color.colorSelectedRow))
            } else {
                row.setBackgroundColor(ContextCompat.getColor(context, R.color.colorTransparent))
            }

            row.setOnClickListener {
                selectedPos = position
                notifyDataSetChanged()
                clickListener(position)
            }
        }
    }

    override fun getItemCount(): Int = item.day.places?.size ?: 0

    class CustomViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameTextView: TextView = view.place_title
        val tempMaxTextView: TextView = view.place_day_max
        val tempMinTextView: TextView = view.place_night_min
        val row: LinearLayout = view.recyclerview_row
    }

}