package tech.hiregus.awave.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.hiregus.awave.databinding.HomeFragmentBinding
import tech.hiregus.awave.itineraries.Itinerary
import timber.log.Timber

class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    private val homeViewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.plant(Timber.DebugTree())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBindings()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupBindings() {
        homeViewModel.uiState.observe(viewLifecycleOwner) { uiState ->
            when (uiState) {
                is HomeUiState.DataState -> displayItineraries(uiState.data)
                HomeUiState.EmptyState -> displayEmptyState(true)
                is HomeUiState.ErrorState -> Timber.e(getString(uiState.resource))
                HomeUiState.LoadingState -> Timber.d("Loading...")
            }
        }
    }

    private fun displayItineraries(itineraries: List<Itinerary>) {
        displayEmptyState(false)
        Timber.d(itineraries.map { it.title }.toString())
    }

    private fun displayEmptyState(active: Boolean) {
        binding.emptyState.isVisible = active
    }


}