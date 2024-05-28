package com.example.unittestingsample

class WeatherForecast(
    val satellite: Satellite,
    val recorder: WeatherRecorder,
    val formatter: WeatherFormatter
) {
    fun shouldBringUmbrella(): Boolean {
        val weather = satellite.getWeather()
        return when (weather) {
            Weather.SUNNY, Weather.CLOUDY -> false
            Weather.RAINY -> true
        }
    }

    fun recordCurrentWeather() {
        val weather = satellite.getWeather()
        val formatted = formatter.format(weather)
        recorder.record(formatted)
    }
}

enum class Weather {
    SUNNY, CLOUDY, RAINY
}

open class Satellite {
    open fun getWeather(): Weather {
        return Weather.SUNNY
    }
}

class StubSatellite(val anyWeather: Weather) : Satellite() {
    override fun getWeather(): Weather {
        return anyWeather
    }
}

class WeatherFormatter {
    fun format(weather: Weather) : String = "Weather is ${weather}"
}

class WeatherRecorder {
    fun record(weather: String) {}
}