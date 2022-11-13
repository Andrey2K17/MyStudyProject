package ru.pg13.mystudyproject.presentation

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import ru.pg13.mystudyproject.R
import ru.pg13.mystudyproject.State
import ru.pg13.mystudyproject.views.CorrectButton
import ru.pg13.mystudyproject.views.CorrectImageButton
import ru.pg13.mystudyproject.views.CorrectProgress
import ru.pg13.mystudyproject.views.CorrectTextView

class FavoriteDataView: LinearLayout {

    private lateinit var checkBox: CheckBox
    private lateinit var textView: CorrectTextView
    private lateinit var changeButton: CorrectImageButton
    private lateinit var actionButton: CorrectButton
    private lateinit var progress: CorrectProgress

    //region constructors
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        init(attrs)
    }
    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(attrs)
    }

    private fun init(attrs: AttributeSet) {
        orientation = VERTICAL
        (context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater)
            .inflate(R.layout.favorite_data_view, this, true)
        checkBox = getChildAt(0) as CheckBox
        val linear = getChildAt(1) as LinearLayout
        textView = linear.findViewById(R.id.textView)
        changeButton = linear.findViewById(R.id.changeButton)
        progress = getChildAt(2) as CorrectProgress
        actionButton = getChildAt(3) as CorrectButton

        progress.visibility = View.INVISIBLE

        context.theme.obtainStyledAttributes(attrs, R.styleable.FavoriteDataView, 0, 0).apply {
            try {
                val actionButtonText = getString(R.styleable.FavoriteDataView_actionButtonText)
                val checkBoxText = getString(R.styleable.FavoriteDataView_checkboxText)
                checkBox.text = checkBoxText
                actionButton.text = actionButtonText
            } finally {
                recycle()
            }
        }
    }


   fun listChanges(block: (checked: Boolean) -> Unit) =
       checkBox.setOnCheckedChangeListener { _, isChecked ->
        block.invoke(isChecked)
    }

    fun handleChangeButton(block: () -> Unit) = changeButton.setOnClickListener {
        block.invoke()
    }

    fun handleActionButton(block: () -> Unit) = actionButton.setOnClickListener {
        Log.d("test123", "dsfadsfsadf")
        block.invoke()
    }

    fun show(state: State) = state.show(progress, actionButton, textView, changeButton)
}