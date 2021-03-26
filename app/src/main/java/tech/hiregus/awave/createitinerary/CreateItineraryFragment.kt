package tech.hiregus.awave.createitinerary

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import tech.hiregus.awave.databinding.CreateItineraryFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

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
    }

    private fun displayConfirm(active: Boolean) {
        if (active) {
            binding.confirm.show()
        } else {
            binding.confirm.hide()
        }
    }

    private fun confirm() {
        viewModel.saveItinerary(binding.title.text.toString())
    }

}