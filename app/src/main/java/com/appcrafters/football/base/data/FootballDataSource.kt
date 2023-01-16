package com.appcrafters.football.base.data

import com.appcrafters.football.base.functional.Either
import com.appcrafters.football.base.model.Football
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call

interface IFootballDataSource {
    suspend fun getAllMatches(): Either<Football>
}

class FootballDataSource(private val apiService: FootballApiService) : IFootballDataSource {
    var host = "livescore-football.p.rapidapi.com"
    var key = "ea9d745207msh8cd03420e084d49p1ac50ajsn5f5967a56706"

    override suspend fun getAllMatches(): Either<Football> = handleCall(apiService.getAllMatches(key, host))

    private suspend fun <T> handleCall(call: Call<T>): Either<T> {
        return withContext(Dispatchers.IO) {
            val response = call.execute()

            if (response.isSuccessful) {
                Either.Success(response.body()!!)
            } else {
                Either.Error(Exception(response.message()))
            }
        }
    }
}