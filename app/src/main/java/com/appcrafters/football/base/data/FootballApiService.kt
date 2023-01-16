package com.appcrafters.football.base.data

import com.appcrafters.football.base.model.Football
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface FootballApiService {

    @GET("matches-by-league?country_code=concacaf&league_code=concacaf-league&timezone_utc=0:00&round=1/16")
    fun getAllMatches(@Header("x-rapidapi-key") key: String, @Header("x-rapidapi-host") host: String): Call<Football>
}