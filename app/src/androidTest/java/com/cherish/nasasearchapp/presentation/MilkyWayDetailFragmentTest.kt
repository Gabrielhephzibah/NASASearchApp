package com.cherish.nasasearchapp.presentation

import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.cherish.nasasearchapp.R
import com.cherish.nasasearchapp.presentation.milkywaydetails.MilkyWayDetailFragment
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
class MilkyWayDetailFragmentTest {

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


    private fun launchMilkyWayDetailFragment() {
        launchFragmentInHiltContainer<MilkyWayDetailFragment> {
            navController.setGraph(R.navigation.nav_graph)
            navController.setCurrentDestination(R.id.milkyWayDetailFragment)
            this.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                if (viewLifecycleOwner != null) {
                    Navigation.setViewNavController(this.requireView(), navController)
                }
            }
        }
    }

    @Test
    fun givenMilkyWayDetailScreenIsDisplayedThenDetailsAreVisible() {

        launchMilkyWayDetailFragment()

        onView(withId(R.id.title)).check(matches(isDisplayed()))
        onView(withId(R.id.description)).check(matches(isDisplayed()))
        onView(withId(R.id.mCenter)).check(matches(isDisplayed()))
        onView(withId(R.id.mDate)).check(matches(isDisplayed()))
        onView(withId(R.id.mImage)).check(matches(isDisplayed()))
    }

    @Test
    fun givenBackArrowIsClickedThenDisplayPreviousScreen() {

        launchMilkyWayDetailFragment()

        onView(withId(R.id.onBackClick)).perform(click())

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.milkyWayFragment)
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}