package com.appcrafters.football.base.model

data class Football(
    val status: String = "",
    val data: Array<Match> = arrayOf(),
)

data class Match(
    val status: String = "",
    val score: Score = Score(),
    val team_1: Team = Team(),
    val team_2: Team = Team(),
    val time: Time2 = Time2(),
)

data class Score(
    val full_time: Time = Time(),
    val half_time: Time = Time(),
)

data class Time2(
    val scheduled: Long = 0,
)

data class Time(
    val team_1: Int = 0,
    val team_2: Int = 0,
)

data class Team(
    val country: String = "",
    val name: String = "",
)
