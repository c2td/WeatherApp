package com.pm.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayoutMediator
import com.pm.weatherapp.ForecastViewModel
import com.pm.weatherapp.R
import com.pm.weatherapp.Utils
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: ForecastViewModel
    private var recyclerViewSelectedPos = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState != null) {
            recyclerViewSelectedPos = savedInstanceState.getInt("RECYCLERVIEW_SELECTED_POS")
        }

        viewModel = ViewModelProvider(this,
            ForecastViewModel.Factory(application)).get(ForecastViewModel::class.java)

        viewModel.forecasts.observe(this, { items ->

            view_pager.adapter = ForecastsViewPagerAdapter(items, recyclerViewSelectedPos)
            val day = Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1
            TabLayoutMediator(tab_layout, view_pager) { tab, position ->
                tab.text =
                    when (position) {
                        0 -> getString(R.string.today)
                        1 -> Utils.getWeekDay((day + 1) % 7)
                        2 -> Utils.getWeekDay((day + 2) % 7)
                        else -> Utils.getWeekDay((day + 3) % 7)
                        // alternative: show month and day on tabs
                        //else -> items[position].date.substring(5)
                    }
            }.attach()
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.run {
            putInt("RECYCLERVIEW_SELECTED_POS", ForecastsViewPagerAdapter.recyclerViewSelectedPos)
        }
        super.onSaveInstanceState(outState)
    }

}