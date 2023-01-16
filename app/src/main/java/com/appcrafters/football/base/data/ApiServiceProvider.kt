package com.appcrafters.football.base.data

object ApiServiceProvider {
    val footballApiService = RetrofitBuilder.retrofit.create(FootballApiService::class.java)
}