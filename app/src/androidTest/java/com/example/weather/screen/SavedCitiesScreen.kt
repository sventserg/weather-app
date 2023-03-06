package com.example.weather.screen

import io.github.kakaocup.kakao.text.KTextView
import com.example.weather.R
import com.example.weather.presentation.fragment.SavedCitiesFragment
import com.kaspersky.kaspresso.screens.KScreen
import io.github.kakaocup.kakao.recycler.KRecyclerView
import io.github.kakaocup.kakao.text.KButton

object SavedCitiesScreen : KScreen<SavedCitiesScreen>() {

    override val layoutId: Int? = R.layout.fragment_saved_cities
    override val viewClass: Class<*>? = SavedCitiesFragment::class.java

    val mainText = KTextView { withId(R.id.mainText)}
    val deleteButton = KButton {withId(R.id.deleteButton)}
    val chooseButton = KButton {withId(R.id.chooseButton)}
    val myLocationButton = KButton {withId(R.id.myLocationButton)}
    val newCityButton = KButton {withId(R.id.newCityButton)}

    val recycler =
        KRecyclerView({ withId(R.id.recyclerViewContainer) }, itemTypeBuilder = { itemType(::CityCard) })

}