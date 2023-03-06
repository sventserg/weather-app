package com.example.weather.screen

import android.view.View
import com.kaspersky.kaspresso.screens.KScreen
import com.example.weather.R
import com.example.weather.presentation.fragment.ChooseCityFragment
import io.github.kakaocup.kakao.edit.KEditText
import io.github.kakaocup.kakao.recycler.KRecyclerView


object ChooseCityScreen : KScreen<ChooseCityScreen>() {
    override val layoutId: Int? = R.layout.fragment_choose_city
    override val viewClass: Class<*>? = ChooseCityFragment::class.java

    val editText = KEditText { withId(R.id.editText) }
    val newCityList =
        KRecyclerView({ withId(R.id.newCityList) }, itemTypeBuilder = { itemType(::CityCard) })

}