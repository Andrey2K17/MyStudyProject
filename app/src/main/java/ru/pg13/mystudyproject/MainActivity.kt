package ru.pg13.mystudyproject

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import ru.pg13.mystudyproject.databinding.ActivityMainBinding
import ru.pg13.mystudyproject.lessons.lesson2.ImageCallback
import ru.pg13.mystudyproject.lessons.lesson2.NetImage

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


/*    атрибут adjustViewBounds очень часто используется чтобы картинка
    заполняла все пространство.*/
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        lesson2Glide()
    }

    private fun lesson2Old() {
        val netImage = NetImage(URL, object : ImageCallback {
            override fun success(bitmap: Bitmap) = runOnUiThread {
                binding.agreementImageView.setImageBitmap(bitmap)
            }

            override fun failed() = runOnUiThread {
                Snackbar.make(binding.agreementImageView, "failed", Snackbar.LENGTH_SHORT).show()
            }
        })

        netImage.start()
    }

    private fun lesson2Picasso() {
        Picasso.get().load(URL).centerCrop()
            .resize(720, 1280)
            .placeholder(android.R.drawable.ic_media_pause)
            .error(android.R.drawable.ic_dialog_alert)
            .into(binding.agreementImageView)
    }

    private fun lesson2Glide() {
        Glide
            .with(this)
            .load(URL)
            //.centerCrop()
            .apply(RequestOptions.circleCropTransform())
            .placeholder(android.R.drawable.ic_media_pause)
            .into(binding.agreementImageView)
    }

    private companion object {
        const val URL =
            "https://www.sport.ru/ai/files/tags_attrs/r1251/26b0a5bcf0e9.jpg"
    }
}