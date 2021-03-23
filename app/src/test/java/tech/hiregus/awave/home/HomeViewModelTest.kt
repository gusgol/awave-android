package tech.hiregus.awave.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import tech.hiregus.awave.MainCoroutineRule
import tech.hiregus.awave.data.City
import tech.hiregus.awave.getOrAwaitValue
import tech.hiregus.awave.itineraries.Itinerary
import tech.hiregus.awave.itineraries.source.FakeItinerariesRepository
import com.google.common.truth.Truth.assertThat
import tech.hiregus.awave.R


@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var homeViewModel: HomeViewModel

    private lateinit var itinerariesRepository: FakeItinerariesRepository

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setupViewModel() {
        itinerariesRepository = FakeItinerariesRepository()
        homeViewModel = HomeViewModel(itinerariesRepository)
    }

    @Test
    fun localItineraries_init_returnsEmpty() {
        homeViewModel.getItineraries()
        val itineraries = homeViewModel.uiState.getOrAwaitValue()
        assertThat(itineraries).isInstanceOf(HomeUiState.EmptyState::class.java)
    }

    @Test
    fun localItineraries_loadAll_returns3() {
        itinerariesRepository.addItineraries(
            Itinerary(title = "Paris day 1", city = City.Paris),
            Itinerary(title = "One afternoon in London", city = City.London),
            Itinerary(title = "Berlin walking tour", city = City.Berlin),
        )
        homeViewModel.getItineraries()
        val itineraries = homeViewModel.uiState.getOrAwaitValue()
        assertThat(itineraries).isInstanceOf(HomeUiState.DataState::class.java)
        assertThat((itineraries as HomeUiState.DataState).data.size).isEqualTo(3)
    }

    @Test
    fun localItineraries_error_returnsError() {
        itinerariesRepository.setReturnError(true)
        homeViewModel.getItineraries()
        val itineraries = homeViewModel.uiState.getOrAwaitValue()
        assertThat(itineraries).isInstanceOf(HomeUiState.ErrorState::class.java)
        assertThat((itineraries as HomeUiState.ErrorState).resource).isEqualTo(R.string.error_load_itineraries)
    }

    @Test
    fun getItineraries_isLoading_startsAndStops() {
        mainCoroutineRule.pauseDispatcher()
        homeViewModel.getItineraries()
        assertThat(homeViewModel.uiState.getOrAwaitValue()).isInstanceOf(HomeUiState.LoadingState::class.java)
        mainCoroutineRule.resumeDispatcher()
        assertThat(homeViewModel.uiState.getOrAwaitValue()).isNotInstanceOf(HomeUiState.LoadingState::class.java)
    }

}