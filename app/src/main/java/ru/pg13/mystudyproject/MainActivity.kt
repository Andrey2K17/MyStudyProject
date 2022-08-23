package ru.pg13.mystudyproject

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import com.google.android.material.snackbar.Snackbar
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.lessons.lesson1.MyClickableSpan

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lesson1()
    }

    private fun lesson1() {
        val fullText = getString(R.string.agrement)
        val confidential = getString(R.string.confidential)
        val policy = getString(R.string.policy)
        val spannableString = SpannableString(fullText)

        val confidentialClickable = MyClickableSpan() {
            Snackbar.make(it, "Go to link1", Snackbar.LENGTH_SHORT).show()
        }

        val policyClickable = MyClickableSpan() {
            Snackbar.make(it, "Go to link2", Snackbar.LENGTH_SHORT).show()
        }

        spannableString.setSpan(
            confidentialClickable,
            fullText.indexOf(confidential),
            fullText.indexOf(confidential) + confidential.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        spannableString.setSpan(
            policyClickable,
            fullText.indexOf(policy),
            fullText.indexOf(policy) + policy.length,
            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        binding.agreementTextView.run {
            text = spannableString
            movementMethod = LinkMovementMethod.getInstance()
            highlightColor = Color.TRANSPARENT
        }

    }
}