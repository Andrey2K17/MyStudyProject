package ru.pg13.mystudyproject.data.net

class JokeCloudDataSource(private val service: JokeService) :
    BaseCloudDataSource<JokeServerModel>() {
    override fun getServerModel() = service.getJoke()
}