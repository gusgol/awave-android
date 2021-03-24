package tech.hiregus.awave.home

import android.os.Bundle
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.unloadKoinModules
import org.koin.core.context.loadKoinModules
import org.koin.core.module.Module
import org.koin.dsl.module
import org.koin.test.KoinTest
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import tech.hiregus.awave.R
import tech.hiregus.awave.data.City
import tech.hiregus.awave.data.Result
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.ItinerariesRepository

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class HomeFragmentTest : KoinTest {

    lateinit var mockModule: Module

    lateinit var itinerariesAndroidRepository: ItinerariesRepository

        private val itineraries = listOf(Itinerary(title = "Paris day 1", city = City.Paris))

    private val empty = listOf<Itinerary>()

    @Before
    fun initRepository() {
        itinerariesAndroidRepository = Mockito.mock(ItinerariesRepository::class.java)
        mockModule = module {
            single(override = true) { itinerariesAndroidRepository }
        }
        loadKoinModules(mockModule)
    }

    @After
    fun tearDown() {
        unloadKoinModules(mockModule)
    }

    @Test
    fun noItineraries_displayEmptyState() {
        // GIVEN - repository returns empty
        runBlocking {
            `when`(itinerariesAndroidRepository.getItineraries()).thenReturn(Result.Success(empty))
        }
        launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_Awave)

        // THEN - empty state is displayed
        onView(withId(R.id.emptyState)).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun hasItineraries_hideEmptyStateAndDisplayList() {
        // GIVEN - repository returns a list
        runBlocking {
            `when`(itinerariesAndroidRepository.getItineraries()).thenReturn(Result.Success(itineraries))
        }
        launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_Awave)

        // THEN - empty state is not displayed
        onView(withId(R.id.emptyState)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withText("Paris day 1")).check(matches(isDisplayed()))
    }

    @Test
    fun emptyState_clickCreate_navigateToCreation() {
        // GIVEN - empty state is there
        runBlocking {
            `when`(itinerariesAndroidRepository.getItineraries()).thenReturn(Result.Success(empty))
        }
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_Awave)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - user clicks the call to action
        onView(withId(R.id.create)).perform(click())

        // THEN - navigates to itinerary creation
        verify(navController).navigate(
            HomeFragmentDirections.actionHomeToSelectCity()
        )
    }

    @Test
    fun fab_click_navigateToCreation() {
        // GIVEN - repository returns a list
        runBlocking {
            `when`(itinerariesAndroidRepository.getItineraries()).thenReturn(Result.Success(itineraries))
        }
        val scenario = launchFragmentInContainer<HomeFragment>(Bundle(), R.style.Theme_Awave)
        val navController = Mockito.mock(NavController::class.java)
        scenario.onFragment {
            Navigation.setViewNavController(it.view!!, navController)
        }

        // WHEN - user clicks the main fab
        onView(withId(R.id.createItineary)).perform(click())

        // THEN - navigates to itinerary creation
        verify(navController).navigate(
            HomeFragmentDirections.actionHomeToSelectCity()
        )
    }

}
