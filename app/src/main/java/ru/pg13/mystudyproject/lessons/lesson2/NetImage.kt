package ru.pg13.mystudyproject.lessons.lesson2

import android.graphics.BitmapFactory
import android.util.Log
import java.lang.Exception
import java.net.URL

class NetImage(
    private val url: String,
    private val callback: ImageCallback
) : Thread() {

    override fun run() {
        super.run()
        try {
            val connection = URL(url).openConnection()
            connection.doInput = true
            connection.connect()
            connection.inputStream.use {
                callback.success(BitmapFactory.decodeStream(it))
            }
        } catch (e: Exception) {
            Log.d("test123","exsption: $e")
            callback.failed()
        }
    }
}