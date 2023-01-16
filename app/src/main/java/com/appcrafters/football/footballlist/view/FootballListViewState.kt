package com.appcrafters.football.footballlist.view

import com.appcrafters.football.base.model.Football

sealed class FootballListViewState {

    object Proccessing : FootballListViewState()
    data class DataReceived(val football: Football) : FootballListViewState()
    data class ErrorReceived(val message: String) : FootballListViewState()
}