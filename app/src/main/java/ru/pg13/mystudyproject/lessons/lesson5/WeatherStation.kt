package ru.pg13.mystudyproject.lessons.lesson5

import java.util.*
import java.util.logging.Handler

class WeatherStation {
    private val people: MutableList<ManBehavior> = LinkedList()
    var degrees = 0

    fun addMan(manBehaviour: ManBehavior) {
        people.add(manBehaviour)
    }

    fun removeMan(manBehaviour: ManBehavior) {
        people.remove(manBehaviour)
    }

    fun updateWeather() {
        degrees = Random().nextInt(400)
        people.forEach {
            it.getCloth(degrees = degrees)
        }

        val handler = android.os.Handler()
        handler.postDelayed({
            updateWeather()
        }, 2000)
    }
}