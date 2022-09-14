package com.cherish.apimodule.repository

import app.cash.turbine.test
import com.cherish.apimodule.utils.MainCoroutineScopeRule
import com.cherish.apimodule.common.Resource
import com.cherish.apimodule.data.remote.MilkyWayApi
import com.cherish.apimodule.data.repository.MilkyWayRepositoryImpl
import com.cherish.apimodule.domain.model.*
import com.cherish.apimodule.domain.model.Collection
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
class MilkyWayRepositoryTest {

    private lateinit var milkyWayRepository: MilkyWayRepositoryImpl

    private val milkyWayApi = mock<MilkyWayApi>()

    private val dispatcher = TestCoroutineDispatcher()

    @get:Rule
    var mainCoroutineRule = MainCoroutineScopeRule()

    @Before
    fun setUp() {
        milkyWayRepository = MilkyWayRepositoryImpl(milkyWayApi, dispatcher)
    }


    @Test
    fun `test getMilkyWayImages_onLoading_false`() = runBlocking {
        given(milkyWayApi.getMilkyWayImages(any(), any(), any(), any())).willReturn(response)

        milkyWayRepository.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test
    fun `get getMilkyWayImages_onSuccess`() = runBlocking {
        given(milkyWayApi.getMilkyWayImages(any(), any(), any(), any())).willReturn(response)

        milkyWayRepository.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            assertThat(Resource.Success(response)).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }
    }

    @Test(expected = Throwable::class)
    fun `test getMilkyWayImages_onError`() = runBlocking {
        given(milkyWayApi.getMilkyWayImages(any(), any(), any(), any())).willThrow(Throwable())

        milkyWayRepository.getMilkyWayImages().test {
            assertThat(Resource.Loading(false)).isEqualTo(awaitItem())
            assertThat(Resource.Error(Throwable())).isEqualTo(awaitItem())
            cancelAndIgnoreRemainingEvents()
        }

    }

    companion object {
        val response = MilkyWay(
            Collection(
                listOf(
                    Item(
                        listOf(
                            Data(
                                "center",
                                "datecreated",
                                "Description",
                                "MediaType",
                                "NasaId",
                                "Title"
                            )
                        ),
                        listOf(Link("Image"))
                    )
                )
            )
        )
    }
}