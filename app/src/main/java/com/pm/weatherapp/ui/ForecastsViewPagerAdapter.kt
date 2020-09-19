package com.pm.weatherapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pm.weatherapp.R
import com.pm.weatherapp.Utils
import com.pm.weatherapp.model.Forecast
import kotlinx.android.synthetic.main.item_view_pager.view.*

class ForecastsViewPagerAdapter(
    private val forecasts: List<Forecast>,
    private val recyclerSelectedPos: Int
) : RecyclerView.Adapter<ForecastsViewPagerAdapter.ViewPagerViewHolder>() {

    companion object {
        var recyclerViewSelectedPos = 0
    }

    private lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_view_pager, parent, false)

        context = parent.context

        return ViewPagerViewHolder(view)
    }

    override fun getItemCount(): Int {
        return forecasts.size
    }

    override fun onBindViewHolder(holder: ViewPagerViewHolder, position: Int) {

        val item = forecasts[position]

        holder.apply {

            displayDayData(itemView, item)
            displayNightData(itemView, item)

            // On upcoming days' tabs the description field is visible by default
            if (position > 0) {
                itemView.description_day.visibility = View.VISIBLE
                itemView.expanded_day.visibility = View.VISIBLE
                itemView.description_night.visibility = View.VISIBLE
                itemView.expanded_night.visibility = View.VISIBLE
            }

            // The following items are only shown on the Today tab
            if (position == 0) {

                itemView.card_view_places.visibility = View.VISIBLE
                var dayCardIconRotation = 0f
                var nightCardIconRotation = 0f

                itemView.expand_day_icon.visibility = View.VISIBLE
                itemView.expand_day_icon.setOnClickListener {
                    dayCardIconRotation = if (dayCardIconRotation == 0f) 180f else 0f
                    itemView.expand_day_icon.animate()
                        .rotation(dayCardIconRotation).setDuration(400).start()

                    if (itemView.expanded_day.visibility == View.GONE) {
                        itemView.expanded_day.visibility = View.VISIBLE
                    } else {
                        itemView.expanded_day.visibility = View.GONE
                    }
                }

                itemView.expand_night_icon.visibility = View.VISIBLE
                itemView.expand_night_icon.setOnClickListener {
                    nightCardIconRotation = if (nightCardIconRotation == 0f) 180f else 0f
                    itemView.expand_night_icon.animate()
                        .rotation(nightCardIconRotation).setDuration(400).start()

                    if (itemView.expanded_night.visibility == View.GONE) {
                        itemView.expanded_night.visibility = View.VISIBLE
                    } else {
                        itemView.expanded_night.visibility = View.GONE
                    }
                }

                // Places listing
                itemView.places_recyclerview.layoutManager = LinearLayoutManager(context)
                itemView.places_recyclerview.adapter =
                    PlacesRecyclerViewAdapter(recyclerSelectedPos, item, context)
                    { position: Int -> onItemClicked(
                        position,
                        item,
                        itemView
                    )
                    }
                itemView.places_recyclerview.addItemDecoration(
                    DividerItemDecoration(
                        context,
                        LinearLayoutManager.VERTICAL
                    )
                )

                // Select the first location by default
                onItemClicked(recyclerSelectedPos, item, itemView)
            }
        }
    }

    private fun displayDayData(view: View, item: Forecast) {
        view.description_day.text = item.day.description
        view.phenomenon_day.text = item.day.phenomenon
        view.max_day.text = context.getString(R.string.temp, item.day.tempMax, "\u00B0")
        view.min_day.text = context.getString(R.string.temp, item.day.tempMin, "\u00B0")
        view.icon_day.setImageResource(Utils.getImageResource(item.day.phenomenon))
        if (item.day.peipsi != null && item.day.peipsi != "") {
            view.description_peipsi_day.text = item.day.peipsi
            view.label_peipsi_day.visibility = View.VISIBLE
            view.description_peipsi_day.visibility = View.VISIBLE
        }
        if (item.day.sea!= null && item.day.sea != "") {
            view.description_sea_day.text = item.day.sea
            view.label_sea_day.visibility = View.VISIBLE
            view.description_sea_day.visibility = View.VISIBLE
        }
    }

    private fun displayNightData(view: View, item: Forecast) {
        view.description_night.text = item.night.description
        view.phenomenon_night.text = item.night.phenomenon
        view.max_night.text = context.getString(R.string.temp, item.night.tempMax, "\u00B0")
        view.min_night.text = context.getString(R.string.temp, item.night.tempMin, "\u00B0")
        view.description_peipsi_night.text = item.night.peipsi
        var icon = Utils.getImageResource(item.night.phenomenon)

        // Switch the sun icon for moon for night view
        if (icon == R.drawable.sun) icon = R.drawable.moon
        view.icon_night.setImageResource(icon)
        if (icon == R.drawable.cloudy) icon = R.drawable.cloudy_night
        view.icon_night.setImageResource(icon)

        if (item.night.peipsi != null && item.night.peipsi!!.isNotEmpty()) {
            view.label_peipsi_night.visibility = View.VISIBLE
            view.description_peipsi_night.visibility = View.VISIBLE
        }

        if (item.night.sea!= null && item.night.sea != "") {
            view.description_sea_night.text = item.night.sea
            view.label_sea_night.visibility = View.VISIBLE
            view.description_sea_night.visibility = View.VISIBLE
        }
    }

    // Update info in details area based on which location was clicked
    private fun onItemClicked(position: Int, item: Forecast, itemView: View) {
        itemView.place_details_title.text = item.day.places?.get(position)?.name
        itemView.place_details_day_max.text = context.getString(R.string.temp,
            item.day.places?.get(position)?.tempMax, "\u00B0")
        itemView.place_day_phenomenon.text = item.day.places?.get(position)?.phenomenon
        itemView.place_details_night_min.text = context.getString(R.string.temp,
            item.night.places?.get(position)?.tempMin, "\u00B0")
        itemView.place_night_phenomenon.text = item.night.places?.get(position)?.phenomenon
        recyclerViewSelectedPos = position
    }

    inner class ViewPagerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}