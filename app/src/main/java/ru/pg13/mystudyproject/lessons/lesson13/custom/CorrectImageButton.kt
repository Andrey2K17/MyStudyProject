package ru.pg13.mystudyproject.lessons.lesson13.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView
import ru.pg13.mystudyproject.lessons.lesson13.ShowImage
import ru.pg13.mystudyproject.lessons.lesson13.ShowText

class CorrectImageButton : AppCompatImageButton, ShowImage {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun show(id: Int) {
        setImageResource(id)
    }
}