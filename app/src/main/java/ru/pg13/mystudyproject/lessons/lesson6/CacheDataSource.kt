package ru.pg13.mystudyproject.lessons.lesson6

import android.content.Context
import android.content.Context.MODE_PRIVATE

class CacheDataSource(context: Context): DataSource {

    private val sharedPreferences = context.getSharedPreferences("counting", MODE_PRIVATE)

    override fun saveInt(key: String, value: Int) {
//        Apply() означает применить изменения сразу и после уже
//асинхронно записать в файл и не блокировать код. Этот метод рекомендуется использовать
//вместо commit.
        sharedPreferences.edit().putInt(key, value).apply()
    }

    override fun getInt(key: String): Int {
        return sharedPreferences.getInt(key, 0)
    }
}