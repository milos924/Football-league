package com.appcrafters.football.footballlist.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appcrafters.football.base.functional.Either
import com.appcrafters.football.footballlist.view.FootballListViewState
import com.appcrafters.football.base.data.IFootballDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FootballListViewModel(private val dataSource: IFootballDataSource) : ViewModel() {

    private val _state = MutableLiveData<FootballListViewState>()
    val state: LiveData<FootballListViewState>
        get() = _state

    fun getMatches() {
        viewModelScope.launch(Dispatchers.IO) {

            _state.postValue(FootballListViewState.Proccessing)

            _state.postValue(
                when (val result = dataSource.getAllMatches()) {
                    is Either.Success -> FootballListViewState.DataReceived(result.data)
                    is Either.Error -> FootballListViewState.ErrorReceived(result.exception.toString())
                }
            )
        }
    }
}