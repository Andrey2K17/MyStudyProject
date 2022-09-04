package ru.pg13.mystudyproject.lessons.lesson8

//class BaseJokeService(private val gson: Gson): JokeService {
//    override fun getJoke(callback: ServiceCallback) {
//        Thread {
//            var connection: HttpURLConnection? = null
//            try {
//                val url = URL(JOKE_URL)
//                connection = url.openConnection() as HttpURLConnection
//                InputStreamReader(BufferedInputStream(connection.inputStream)).use {
//                    val line: String = it.readText()
//                    Log.d("test123", "line: $line")
//                    val dto = gson.fromJson(line, JokeDTO::class.java)
//                    callback.returnSuccess(dto)
//                }
//            } catch (e: Exception) {
//                Log.d("test123", "e: $e")
//                if (e is UnknownHostException)
//                    callback.returnError(ErrorType.NO_CONNECTION)
//                else
//                    callback.returnError(ErrorType.OTHER)
//            } finally {
//                connection?.disconnect()
//            }
//        }.start()
//    }
//
//    private companion object {
//        const val JOKE_URL = "http://api.icndb.com/jokes/random/"
//    }
//}