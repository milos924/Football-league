package com.appcrafters.football.base.data

import com.appcrafters.football.base.functional.Either
import com.appcrafters.football.base.model.Football
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType
import okhttp3.ResponseBody
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations.openMocks
import retrofit2.Call
import retrofit2.Response

class FootballDataSourceTests {

    @Mock
    lateinit var apiService: FootballApiService

    @Mock
    lateinit var getMatchesCall: Call<Football>

    lateinit var dataSource: FootballDataSource

    @Before
    fun setUp() {
        openMocks(this)
        dataSource = FootballDataSource(apiService)
    }

    @Test
    fun `testGetMatches, has response, Success returned`() = runBlocking {
        val expectedMatches: Football = Football()
        val expectedResult = Either.Success(expectedMatches)

        `when`(apiService.getAllMatches(anyString(), anyString())).thenReturn(getMatchesCall)
        `when`(getMatchesCall.execute()).thenReturn(Response.success(expectedMatches))

        val result = dataSource.getAllMatches()

        assertEquals(expectedResult, result)
    }

    @Test
    fun `testGetMatches, has error, Error returned`() = runBlocking {
        val expectedResponseBody = ResponseBody.create(
            MediaType.parse("application/json"), ""
        )

        `when`(apiService.getAllMatches(anyString(), anyString())).thenReturn(getMatchesCall)
        `when`(getMatchesCall.execute()).thenReturn(Response.error(400, expectedResponseBody))


        val result = dataSource.getAllMatches()

        assertTrue(result is Either.Error)
    }
}