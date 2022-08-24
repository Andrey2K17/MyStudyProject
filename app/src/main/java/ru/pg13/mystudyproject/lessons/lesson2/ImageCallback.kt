package ru.pg13.mystudyproject.lessons.lesson2

import android.graphics.Bitmap

interface ImageCallback {

    fun success(bitmap: Bitmap)

    fun failed()
}
