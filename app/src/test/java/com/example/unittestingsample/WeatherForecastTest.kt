package com.example.unittestingsample

import org.junit.Test
import org.assertj.core.api.Assertions.*
import com.nhaarman.mockitokotlin2.*
import org.junit.Before

class WeatherForecastTest {
    lateinit var satellite: Satellite
    lateinit var weatherForecast: WeatherForecast

    @Before
    fun setUp() {
        satellite = mock(name = "MockSatellite")
        whenever(satellite.getWeather()).thenReturn(Weather.SUNNY)

        val recorder = WeatherRecorder()
        val formatter = WeatherFormatter()
        weatherForecast = WeatherForecast(satellite, recorder, formatter)
    }

    @Test
    fun shouldBringUmbrella_givenSunny_returnsFalse() {
        val actual = weatherForecast.shouldBringUmbrella()
        assertThat(actual).isFalse()
    }
}