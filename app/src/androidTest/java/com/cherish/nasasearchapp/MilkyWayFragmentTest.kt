package com.cherish.nasasearchapp

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cherish.nasasearchapp.presentation.milkyway.MilkyWayFragment
import com.cherish.nasasearchapp.utils.MockServerDispatcher
import com.cherish.nasasearchapp.utils.launchFragmentInHiltContainer
import com.google.common.truth.Truth.assertThat
import com.jakewharton.espresso.OkHttp3IdlingResource
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class MilkyWayFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var okHttp: OkHttpClient

    private lateinit var mockWebServer: MockWebServer


    private val navController = TestNavHostController(ApplicationProvider.getApplicationContext())


    @Before
    fun setUp() {
        hiltRule.inject()
        mockWebServer = MockWebServer()
        mockWebServer.start(8080)
        IdlingRegistry.getInstance().register(OkHttp3IdlingResource.create("okHttp", okHttp))
    }

    private fun launchMilkyWayFragment() {
        launchFragmentInHiltContainer<MilkyWayFragment> {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.milkyWayFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }


    @Test
    fun givenMilkyWayItemIsClickedThenNavigateToMilkyWayDetailScreen() {

        launchMilkyWayFragment()

        mockWebServer.dispatcher = MockServerDispatcher().RequestDispatcher()

        onView(withId(R.id.recyclerView)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                1,
                click()
            )
        )
        assertThat(navController.currentDestination?.id).isEqualTo(R.id.milkyWayDetailFragment)

        val currentDestinationArgs = navController.backStack.last().arguments

        assertThat(currentDestinationArgs).isNotNull()

    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }

}