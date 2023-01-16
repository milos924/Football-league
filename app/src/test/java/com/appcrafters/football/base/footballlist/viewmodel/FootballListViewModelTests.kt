package com.appcrafters.football.base.footballlist.viewmodel

import androidx.lifecycle.Observer
import com.appcrafters.football.base.InstantExecutorTest
import com.appcrafters.football.base.data.IFootballDataSource
import com.appcrafters.football.base.functional.Either
import com.appcrafters.football.base.model.Football
import com.appcrafters.football.footballlist.view.FootballListViewState
import com.appcrafters.football.footballlist.view.FootballListViewState.*
import com.appcrafters.football.footballlist.viewmodel.FootballListViewModel
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations.openMocks

class FruitListViewModelTests : InstantExecutorTest() {
    @Mock
    lateinit var dataSource: IFootballDataSource

    @Mock
    lateinit var stateObserver: Observer<FootballListViewState>

    lateinit var viewModel: FootballListViewModel

    @Before
    fun setUp() {
        openMocks(this)
        viewModel = FootballListViewModel(dataSource)
        viewModel.state.observeForever(stateObserver)
    }

    @Test
    fun `testGetMatches, has result, state changed to Proccessing - DataReceived`() = runBlocking {
        val expectedResult: Football = Football()

        `when`(dataSource.getAllMatches()).thenReturn(Either.Success(expectedResult))

        viewModel.getMatches()

        verify(stateObserver).onChanged(Proccessing)
        verify(stateObserver).onChanged(DataReceived(expectedResult))
    }

    @Test
    fun `testGetMatches, has error, state changed to Proccessing - ErrorReceived`() = runBlocking {
        val expectedError = Exception("test")

        `when`(dataSource.getAllMatches()).thenReturn(Either.Error(expectedError))

        viewModel.getMatches()

        verify(stateObserver).onChanged(ErrorReceived(expectedError.toString()))
    }
}