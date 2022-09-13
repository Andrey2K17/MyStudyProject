package ru.pg13.mystudyproject.lessons.lesson13.custom

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import ru.pg13.mystudyproject.lessons.lesson13.ShowText

class CorrectTextView : AppCompatTextView, ShowText {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun show(text: String) {
        setText(text)
    }
}