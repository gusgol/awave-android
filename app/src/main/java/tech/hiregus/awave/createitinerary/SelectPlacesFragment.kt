package tech.hiregus.awave.createitinerary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import tech.hiregus.awave.databinding.FragmentSelectPlacesBinding


class SelectPlacesFragment : Fragment() {

    companion object {
        const val ARG_CITY = "city"
    }

    private var _binding: FragmentSelectPlacesBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SelectPlacesViewModel by viewModel {
        parametersOf(arguments?.getSerializable(ARG_CITY))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectPlacesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding.city.setText(viewModel.city?.displayName ?: -1)
    }

}