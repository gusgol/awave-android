package tech.hiregus.awave.createitinerary

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import tech.hiregus.awave.databinding.CreateItineraryFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import tech.hiregus.awave.R
import tech.hiregus.awave.data.City

class CreateItineraryFragment : Fragment() {

    private var _binding: CreateItineraryFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CreateItineraryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = CreateItineraryFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        binding.title.requestFocus()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViews() {
        // Toolbar
        binding.toolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        // Fab
        binding.confirm.setOnClickListener {
            confirm()
        }

        // Title
        binding.title.doAfterTextChanged {
            displayConfirm(!it.isNullOrBlank())
        }

        // Cities
        binding.cities.adapter = CitiesPagerAdapter(City.values().toList())
    }

    private fun displayConfirm(active: Boolean) {
        if (active) {
            binding.confirm.show()
        } else {
            binding.confirm.hide()
        }
    }

    private fun confirm() {
        val city = City.values()[binding.cities.currentItem]
        viewModel.saveItinerary(
            title = binding.title.text.toString(),
            city = city
        )
        val bundle = bundleOf(SelectPlacesFragment.ARG_CITY to city)
        binding.confirm.findNavController().navigate(R.id.action_selectCity_to_selectPlaces, bundle)
    }

}