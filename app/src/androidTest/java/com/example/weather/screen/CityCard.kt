package com.example.weather.screen

import android.view.View
import com.example.weather.R
import io.github.kakaocup.kakao.recycler.KRecyclerItem
import io.github.kakaocup.kakao.text.KTextView
import org.hamcrest.Matcher

class CityCard(parent: Matcher<View>) : KRecyclerItem<CityCard>(parent) {
    val cityName = KTextView(parent) { withId(R.id.cityName) }
    val countryName = KTextView(parent) { withId(R.id.countryName) }

}