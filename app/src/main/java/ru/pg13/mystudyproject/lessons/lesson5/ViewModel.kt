package ru.pg13.mystudyproject.lessons.lesson5

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
        model.start(textCallback)
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

class Model() {
    private var timer: Timer? = null
    private val timerTask = object : TimerTask() {
        override fun run() {
            count++
            callback?.updateText(count.toString())
        }
    }
    private var count = 0
    private var callback: TextCallback? = null

    fun start(textCallback: TextCallback) {
        callback = textCallback
        timer?.cancel()
        timer = Timer()
        timer?.scheduleAtFixedRate(timerTask, 1000, 1000)
    }
}