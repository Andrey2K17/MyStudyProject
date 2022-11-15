package ru.pg13.mystudyproject.presentation

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatTextView

class CorrectButton : AppCompatButton, EnableView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun enable(enabled: Boolean) {
        isEnabled = enabled
    }
}

class CorrectImageButton : AppCompatImageButton, ShowImage {
    //region constructors
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)
    //endregion

    override fun show(arg: Int) {
        setImageResource(arg)
    }
}

class CorrectProgress : ProgressBar, ShowView {
    constructor(context: Context): super(context)
    constructor(context: Context, attrs: AttributeSet): super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int): super(context, attrs, defStyleAttr)

    override fun show(arg: Boolean) {
        visibility = if (arg) View.VISIBLE else View.INVISIBLE
    }
}

class CorrectTextView : AppCompatTextView, ShowText {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun show(arg: String) {
        text = arg
    }
}