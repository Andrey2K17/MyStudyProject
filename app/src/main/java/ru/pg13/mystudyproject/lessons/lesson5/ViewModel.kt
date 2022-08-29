package ru.pg13.mystudyproject.lessons.lesson5

import android.content.ContentValues.TAG
import android.util.Log
import ru.pg13.mystudyproject.lessons.lesson6.DataSource
import java.util.*

class ViewModel(private val model: Model) {

    private var textObservable: TextObservable? = null

    private val textCallback = object : TextCallback {
        override fun updateText(str: String) {
            textObservable?.postValue(str)
        }
    }

    fun init(textObservable: TextObservable) {
        this.textObservable = textObservable
    }

    fun resumeCounting() {
        model.start(textCallback)
    }
    fun pauseCounting() {
        model.stop()
    }

    fun clear() {
        textObservable = null
    }
}

class TextObservable {
    private lateinit var callback: TextCallback

    fun observe(callback: TextCallback) {
        this.callback = callback
    }

    fun postValue(text: String) {
        callback.updateText(text)
    }
}

interface TextCallback {
    fun updateText(str: String)
}

class Model(private val dataSource: DataSource) {
    private var timer: Timer? = null
    private val timerTask = object : TimerTask() {
        override fun run() {
            count++
            callback?.updateText(count.toString())
        }
    }
    private var count = -1
    private var callback: TextCallback? = null

    fun start(textCallback: TextCallback) {
        callback = textCallback
        Log.d(TAG, "start: count is $count")
        if (count < 0)
            count = dataSource.getInt("counting")
        Log.d(TAG, "started with count $count")
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 1000, 1000)
    }

    fun stop() {
        Log.d(TAG, "stop with count $count")
        dataSource.saveInt("counting", count)
        timer?.cancel()
        timer = null
    }
}