package com.cherish.apimodule.domain

import app.cash.turbine.test
import com.cherish.apimodule.MainCoroutineScopeRule
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.data.remote.MilkyWayApi
import com.cherish.apimodule.data.repository.*
import com.cherish.apimodule.domain.usecases.MilkyWayUseCasesImpl
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.given
import org.mockito.kotlin.mock

@ExperimentalCoroutinesApi
class MilkyWayUseCaseTest {
    private lateinit var milkyWayUseCases: MilkyWayUseCasesImpl


    private val milkyWayService = mock<MilkyWayApi>()


    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        milkyWayUseCases = MilkyWayUseCasesImpl(milkyWayService, dispatcher)
    }


    @Test
    fun `test getMilkyWayImages_onLoading_false`() = runBlocking {
        given(milkyWayService.getMilkyWayImages(any(), any(), any(), any())).willReturn(response)
        milkyWayUseCases.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `get getMilkyWayImages_onSuccess`() = runBlocking {
        given(milkyWayService.getMilkyWayImages(any(), any(), any(), any())).willReturn(response)

        milkyWayUseCases.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            assertThat(Resource.Success(response)).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(expected = Throwable::class)
    fun `test getMilkyWayImages_onError`() = runBlocking {
        given(milkyWayService.getMilkyWayImages(any(), any(), any(), any())).willThrow(Throwable())

        milkyWayUseCases.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            assertThat(Resource.Error(Throwable())).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }


    companion object{
        val response = MilkyWay(
            Collection(
            listOf(
            Item(listOf(
                Data(
            "center",
            "datecreated",
            "Description",
            "MediaType",
            "NasaId",
            "Title")
            ),
            listOf(Link("Image")))
        ))
        )
    }
}