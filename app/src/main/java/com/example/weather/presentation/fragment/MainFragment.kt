package com.example.weather.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.weather.App
import com.example.weather.R
import com.example.weather.data.*
import com.example.weather.data.storage.PRESS_CITY_BUTTON
import com.example.weather.databinding.MainFragmentBinding
import kotlinx.coroutines.launch

/**
 * MainFragment - start application fragment
 */

class MainFragment : Fragment() {

    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!
    private val viewModel = App.daggerComponent.mainViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater)
        return binding.root
    }

    /**
     * Visibility of fragment items during loading current weather information
     */
    private fun loadingVision() {
        binding.cityAnimation.visibility = View.VISIBLE
        binding.weatherAnimation.visibility = View.GONE
        binding.lastUpdate.visibility = View.GONE
        binding.currentTemperatureText.visibility = View.GONE
        binding.currentWindSpeedText.visibility = View.GONE
        binding.cityName.visibility = View.GONE
        binding.refreshButton.visibility = View.GONE
        binding.chooseCityButton.visibility = View.GONE
        binding.dailyWeather.visibility = View.GONE
    }

    /**
     * Information for accessibility mode
     */
    private fun accessibility() {
        //Current weather text
        binding.weatherText.importantForAccessibility = 2
        binding.temperatureImage.importantForAccessibility = 2
        binding.temperaturePostfix.importantForAccessibility = 2
        //Current wind speed
        binding.windSpeedPostfix.importantForAccessibility = 2
        binding.windImage?.importantForAccessibility = 2
        binding.windSpeedText.importantForAccessibility = 2
        //Loading animation
        binding.cityAnimation.contentDescription = getString(R.string.loading)
        //Last update
        binding.lastUpdate.importantForAccessibility = 2
        binding.lastUpdateText.importantForAccessibility = 2
        //Morning temperature
        binding.morningText?.importantForAccessibility = 2
        binding.morningImage?.importantForAccessibility = 2
        binding.morningWeather.importantForAccessibility = 2
        //Day temperature
        binding.dayText?.importantForAccessibility = 2
        binding.dayImage?.importantForAccessibility = 2
        binding.dayWeather.importantForAccessibility = 2
        //Evening temperature
        binding.eveningText?.importantForAccessibility = 2
        binding.eveningImage?.importantForAccessibility = 2
        binding.eveningWeather.importantForAccessibility = 2
        //Night temperature
        binding.nightText?.importantForAccessibility = 2
        binding.nightImage?.importantForAccessibility = 2
        binding.nightWeather.importantForAccessibility = 2
    }

    /**
     * Visibility of fragment items after loading of current weather information
     */
    private fun loadedVision() {
        binding.cityAnimation.visibility = View.GONE
        binding.windSpeedPostfix.visibility = View.VISIBLE
        binding.temperaturePostfix.visibility = View.VISIBLE
        binding.weatherAnimation.visibility = View.VISIBLE
        binding.lastUpdate.visibility = View.VISIBLE
        binding.currentTemperatureText.visibility = View.VISIBLE
        binding.currentWindSpeedText.visibility = View.VISIBLE
        binding.cityName.visibility = View.VISIBLE
        binding.refreshButton.visibility = View.VISIBLE
        binding.chooseCityButton.visibility = View.VISIBLE
        binding.dailyWeather.visibility = View.VISIBLE
    }

    /**
     * Set animation and content description according to weather code
     */
    private fun checkWeatherCode() {
        when (viewModel.getCurrentWeatherCode()) {
            //Sunny
            CLEAR_SKY -> {
                binding.weatherAnimation.setAnimation(R.raw.weather_sunny)
                binding.weatherAnimation.contentDescription = getString(R.string.sunny)
            }
            //Partly cloudy
            MAINLY_CLEAR, PARTLY_CLOUDY,
            OVERCAST -> {
                binding.weatherAnimation.setAnimation(R.raw.weather_partly_cloudy)
                binding.weatherAnimation.contentDescription = getString(R.string.partly_cloudy)
            }
            //Fog
            FOG, DEPOSITING_RIME_FOG -> {
                binding.weatherAnimation.setAnimation(R.raw.foggy)
                binding.weatherAnimation.contentDescription = getString(R.string.foggy)
            }
            //Rain
            LIGHT_DRIZZLE, MODERATE_DRIZZLE,
            DENSE_INTENSITY_DRIZZLE, LIGHT_FREEZING_DRIZZLE,
            DENSE_INTENSITY_FREEZING_DRIZZLE, SLIGHT_RAIN,
            MODERATE_RAIN, HEAVY_INTENSITY_RAIN,
            LIGHT_FREEZING_RAIN, HEAVY_INTENSITY_FREEZING_RAIN -> {
                binding.weatherAnimation.setAnimation(R.raw.weather_partly_shower)
                binding.weatherAnimation.contentDescription = getString(R.string.rain)
            }
            //Snow
            SLIGHT_SNOW_FALL, MODERATE_SNOW_FALL,
            HEAVY_INTENSITY_SNOW_FALL, SNOW_GRAINS -> {
                binding.weatherAnimation.setAnimation(R.raw.weather_snow)
                binding.weatherAnimation.contentDescription = getString(R.string.snow)
            }
            //Rain showers
            SLIGHT_RAIN_SHOWERS, MODERATE_RAIN_SHOWERS,
            VIOLENT_RAIN_SHOWERS, SLIGHT_SNOW_SHOWERS,
            HEAVY_SNOW_SHOWERS, THUNDERSTORM,
            THUNDERSTORM_WITH_SLIGHT_HAIL, THUNDERSTORM_WITH_HEAVY_HAIL -> {
                binding.weatherAnimation.setAnimation(R.raw.weather_stormshowersday)
                binding.weatherAnimation.contentDescription = getString(R.string.rain_showers)
            }
        }
    }

    /**
     * Checking temperature text for prefix "-"
     * if text doesn't start with "-" add "+"
     * if text starts with "-" does nothing
     */
    private fun checkTemperatureText(text: String): String {
        if (!text.startsWith("-")) {
            return "+$text"
        } else return text
    }

    /**
     * Updating text information and content description
     */
    private fun textUpdate() {
        val currentWindSpeed = viewModel.getCurrentWindSpeed()
        val currentTemperature = checkTemperatureText(viewModel.getCurrentTemperature())
        val morningTemperature = checkTemperatureText(viewModel.getMorningTemperature())
        val dayTemperature = checkTemperatureText(viewModel.getDayTemperature())
        val eveningTemperature = checkTemperatureText(viewModel.getEveningTemperature())
        val nightTemperature = checkTemperatureText(viewModel.getNightTemperature())

        //Update of text information
        binding.lastUpdateText.text = viewModel.getLastUpdate()
        binding.cityName.text = viewModel.getLastCityName()
        binding.windSpeedText.text = currentWindSpeed
        binding.weatherText.text = currentTemperature
        binding.morningWeather.text = morningTemperature
        binding.dayWeather.text = dayTemperature
        binding.eveningWeather.text = eveningTemperature
        binding.nightWeather.text = nightTemperature

        //Accessibility content description
        binding.currentTemperatureText.contentDescription =
            getString(R.string.temperature) + currentTemperature
        binding.currentWindSpeedText.contentDescription =
            getString(R.string.wind_speed) + currentWindSpeed
        binding.morningData?.contentDescription =
            getString(R.string.morning_temperature) + morningTemperature
        binding.dayData?.contentDescription =
            getString(R.string.day_temperature) + dayTemperature
        binding.eveningData?.contentDescription =
            getString(R.string.evening_temperature) + eveningTemperature
        binding.nightData?.contentDescription =
            getString(R.string.night_temperature) + nightTemperature
        binding.lastUpdateText.importantForAccessibility = 1
    }

    /**
     * Updating current weather information
     */
    private fun weatherUpdate() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getCurrentCityWeather()
            checkWeatherCode()
            loadedVision()
            textUpdate()
        }
    }

    /**
     * Visibility of fragment items for first usage of application
     */
    private fun startText() {
        binding.cityAnimation.visibility = View.VISIBLE
        binding.weatherAnimation.visibility = View.GONE
        binding.windSpeedPostfix.visibility = View.GONE
        binding.temperaturePostfix.visibility = View.GONE
        binding.dailyWeather.visibility = View.GONE
        binding.lastUpdateText.text = viewModel.getLastUpdate()
        binding.cityName.text = viewModel.getLastCityName()
        binding.windSpeedText.text = viewModel.getCurrentWindSpeed()
        binding.weatherText.text = viewModel.getCurrentTemperature()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        accessibility()
        if (viewModel.getLastCityName() == PRESS_CITY_BUTTON) {
            startText()
        } else {
            loadingVision()
            weatherUpdate()
        }

        //Refresh button
        binding.refreshButton.setOnClickListener {
            binding.weatherAnimation.visibility = View.GONE
            weatherUpdate()
        }

        //Choose city button
        binding.chooseCityButton.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_savedCitiesFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}