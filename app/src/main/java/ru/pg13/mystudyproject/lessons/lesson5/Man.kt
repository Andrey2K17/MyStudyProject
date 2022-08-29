package ru.pg13.mystudyproject.lessons.lesson5

import android.util.Log

class Man(val name: String, val surname: String): ManBehavior {
    private val TAG = Man::class.java.simpleName

    override fun getCloth(degrees: Int) {
        if (degrees > 200) {
            Log.d(TAG, "warm")
        } else {
            Log.d(TAG, "cold")
        }
    }
}