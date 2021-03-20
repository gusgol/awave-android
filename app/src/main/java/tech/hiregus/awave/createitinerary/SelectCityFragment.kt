package tech.hiregus.awave.createitinerary

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import tech.hiregus.awave.R

class SelectCityFragment : Fragment() {

    private lateinit var viewModel: SelectCityViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.select_city_fragment, container, false)
    }

}