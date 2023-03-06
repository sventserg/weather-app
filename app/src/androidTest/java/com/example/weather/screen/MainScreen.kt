package com.example.weather.screen

import com.kaspersky.kaspresso.screens.KScreen
import com.example.weather.R
import com.example.weather.presentation.fragment.MainFragment
import io.github.kakaocup.kakao.image.KImageView
import io.github.kakaocup.kakao.text.KButton
import io.github.kakaocup.kakao.text.KTextView

object MainScreen : KScreen<MainScreen>() {

    override val layoutId: Int? = R.layout.main_fragment
    override val viewClass: Class<*>? = MainFragment::class.java

    //Current information
    val cityNameText = KTextView { withId(R.id.cityName)}
    val temperatureImage = KImageView { withId(R.id.temperatureImage)}
    val windImage = KImageView { withId(R.id.windImage)}
    val currentTemperatureText = KTextView { withId(R.id.weatherText)}
    val temperaturePostfix = KTextView { withId(R.id.temperaturePostfix)}
    val currentWindSpeedText = KTextView { withId(R.id.windSpeedText)}
    val windSpeedPostfix = KTextView { withId(R.id.windSpeedPostfix)}
    val textLastUpdate = KTextView { withId(R.id.textLastUpdate)}
    val lastUpdateText = KTextView { withId(R.id.lastUpdateText)}

    //Daily weather information
    val morningWeather = KTextView { withId(R.id.morningWeather)}
    val dayWeather = KTextView { withId(R.id.dayWeather)}
    val eveningWeather = KTextView { withId(R.id.eveningWeather)}
    val nightWeather = KTextView { withId(R.id.nightWeather)}

    val morningImage = KImageView { withId(R.id.morningImage)}
    val dayImage = KImageView { withId(R.id.dayImage)}
    val eveningImage = KImageView { withId(R.id.eveningImage)}
    val nightImage = KImageView { withId(R.id.nightImage)}

    val morningText = KTextView { withId(R.id.morningText)}
    val dayText = KTextView { withId(R.id.dayText)}
    val eveningText = KTextView { withId(R.id.eveningText)}
    val nightText = KTextView { withId(R.id.nightText)}

    //Buttons
    val refreshButton = KButton { withId(R.id.refreshButton)}
    val chooseCityButton = KButton { withId(R.id.chooseCityButton)}

    val animation = KImageView { withId(R.id.cityAnimation)}

}