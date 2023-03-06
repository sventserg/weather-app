package com.example.weather

import android.Manifest
import androidx.test.ext.junit.rules.activityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.GrantPermissionRule
import androidx.test.uiautomator.UiDevice
import com.example.weather.presentation.MainActivity
import com.example.weather.screen.ChooseCityScreen
import com.example.weather.screen.CityCard
import com.example.weather.screen.MainScreen
import com.example.weather.screen.SavedCitiesScreen
import com.kaspersky.kaspresso.kaspresso.Kaspresso
import com.kaspersky.kaspresso.testcases.api.testcase.TestCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test

class UITests : TestCase(
    kaspressoBuilder = Kaspresso.Builder.simple(
        customize = {
            if (isAndroidRuntime) {
                UiDevice
                    .getInstance(instrumentation)
                    .executeShellCommand("appops set --uid ${InstrumentationRegistry.getInstrumentation().targetContext.packageName} MANAGE_EXTERNAL_STORAGE allow")
            }
        }
    )
) {

    @get:Rule
    val runtimePermissionRule: GrantPermissionRule = GrantPermissionRule.grant(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    @get:Rule
    val activityRule = activityScenarioRule<MainActivity>()

    @Test
    fun addNewCityTest() = run {
        step("Checking main fragment") {
            testLogger.i("step 1")
            runBlocking { delay(10_000) }
            MainScreen {
                cityNameText { isVisible() }
                temperatureImage { isVisible() }
                windImage { isVisible() }
                currentTemperatureText { isVisible() }
                temperaturePostfix { isVisible() }
                currentWindSpeedText { isVisible() }
                windSpeedPostfix { isVisible() }
                textLastUpdate { isVisible() }
                lastUpdateText { isVisible() }
                morningWeather { isVisible() }
                dayWeather { isVisible() }
                eveningWeather { isVisible() }
                nightWeather { isVisible() }
                morningImage { isVisible() }
                dayImage { isVisible() }
                eveningImage { isVisible() }
                nightImage { isVisible() }
                morningText { isVisible() }
                dayText { isVisible() }
                eveningText { isVisible() }
                nightText { isVisible() }
                chooseCityButton {
                    isVisible()
                    isClickable()
                    isEnabled()
                }
                refreshButton {
                    isVisible()
                    isClickable()
                    isEnabled()
                }
            }
        }

        step("Navigate to SavedCitiesFragment") {
            testLogger.i("step 2")
            MainScreen {
                chooseCityButton.click()
            }
        }

        step("Navigate to ChooseCityFragment") {
            testLogger.i("step 3")
            SavedCitiesScreen {
                newCityButton.click()
            }
        }

        step("Finding new city") {
            testLogger.i("step 4")

            ChooseCityScreen {
                editText.typeText("Moscow")
                runBlocking { delay(10000) }
                newCityList {
                    firstChild<CityCard> {
                        isVisible()
                        click()
                    }
                }
            }
        }
    }

    @Test
    fun addAnotherNewCityTest() = run {
        step("Add another city") {
            testLogger.i("step 1")
            MainScreen.chooseCityButton.click()
            SavedCitiesScreen.newCityButton.click()
            ChooseCityScreen {
                editText.typeText("Berlin")
                newCityList.firstChild<CityCard> { click() }
            }
        }

    }

    @Test
    fun deleteCity() = run {
        step("Navigate to SavedCitiesFragment") {
            testLogger.i("step 1")
            MainScreen.chooseCityButton.click()
        }

        step("Choose city and delete it") {
            testLogger.i("step 2")
            SavedCitiesScreen {
                recycler {
                    firstChild<CityCard> { click() }
                }
                deleteButton.click()
            }
        }
        step("Choose another city") {
            testLogger.i("step 3")
            SavedCitiesScreen {
                recycler.firstChild<CityCard> { click() }
                chooseButton.click()
            }
            runBlocking { delay(1000) }
        }
    }
}