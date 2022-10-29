package com.chirikualii.materiapi.data.repository

import android.util.Log
import com.chirikualii.materiapi.data.model.Movie
import com.chirikualii.materiapi.data.remote.ApiService

class MovieRepoImpl(private val service: ApiService) : MovieRepo {
    override suspend fun getpopularmovie() : List<Movie> {
        try {
            val response = service.getPopularMovie()

            if (response.isSuccessful) {
                val ListMovie = response.body()
                val ListData = ListMovie?.results?.map {
                    Movie(
                        title = it.title,
                        genre = it.releaseDate,
                        imagePoster = it.posterPath
                    )
                }
                return ListData ?: emptyList()
            } else {
                Log.e(MovieRepoImpl::class.simpleName, "getPopularMovie error :${response.body()}",)
            }
            return emptyList()
        } catch (e: Exception) {
            Log.e(
                MovieRepoImpl::class.simpleName,
                "getPopularMovie error : code :${e.message}",
            )
            return emptyList()
        }
    }
}